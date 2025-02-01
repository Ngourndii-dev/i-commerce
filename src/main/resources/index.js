
import pkg from 'pg'; 
const { Client } = pkg;

import { faker } from '@faker-js/faker'; 

const client = new Client({
    host: 'localhost',
    port: 5432,
    user: 'postgres',
    password: '1234',
    database: 'i_commerce',
});

function truncateString(str, length) {
    return str.length > length ? str.slice(0, length) : str;
}

async function insertStores() {
    for (let i = 0; i < 10; i++) {
        const title = faker.company.name();
        const location = faker.location.city();
        const pub = faker.internet.url();
        
        const query = `
            INSERT INTO store (title, location, pub)
            VALUES ($1, $2, $3);
        `;
        const values = [title, location, pub];
        try {
            await client.query(query, values);
            console.log(`Inserted store ${i + 1}`);
        } catch (err) {
            console.error('Error inserting store:', err.stack);
        }
    }
}

async function insertCategories() {
    const storeRes = await client.query('SELECT id_store FROM store');
    const storeIds = storeRes.rows.map(row => row.id_store);
    
    let categoriesSet = new Set();  
    for (let i = 0; i < 10; i++) {
        let category_name = faker.commerce.department();
        category_name = truncateString(category_name, 50); 
        const description = truncateString(faker.lorem.sentence(), 255);
        const unity = truncateString(faker.word.noun(), 50);

        if (categoriesSet.has(category_name)) {
            console.log(`Skipping duplicate category: ${category_name}`);
            continue;  
        }

        categoriesSet.add(category_name);  

        const existingCategoryRes = await client.query('SELECT 1 FROM category WHERE category_name = $1 LIMIT 1', [category_name]);
        if (existingCategoryRes.rows.length === 0) {
            const store_id = storeIds[Math.floor(Math.random() * storeIds.length)];
            const query = `
                INSERT INTO category (store, category_name, description, unity)
                VALUES ($1, $2, $3, $4);
            `;
            const values = [store_id, category_name, description, unity];
            try {
                await client.query(query, values);
                console.log(`Inserted category ${i + 1}`);
            } catch (err) {
                console.error('Error inserting category:', err.stack);
            }
        } else {
            console.log(`Category already exists in the database: ${category_name}`);
        }
    }
}

async function insertProducts() {
    const categoryRes = await client.query('SELECT id_category FROM category');
    const categoryIds = categoryRes.rows.map(row => row.id_category);
    
    for (let i = 0; i < 10; i++) {
        const category_id = categoryIds[Math.floor(Math.random() * categoryIds.length)];
        const price = parseFloat(faker.commerce.price());
        const sizesArray = ['S', 'M', 'L', 'XL'];
        const sizes = faker.helpers.arrayElement(sizesArray) || 'M'; 
        const color = faker.color.human(); 

        const query = `
            INSERT INTO product (category, price, sizes, color)
            VALUES ($1, $2, $3, $4);
        `;
        const values = [category_id, price, sizes, color];
        try {
            await client.query(query, values);
            console.log(`Inserted product ${i + 1}`);
        } catch (err) {
            console.error('Error inserting product:', err.stack);
        }
    }
}

async function insertClients() {
  for (let i = 0; i < 10; i++) {
      const full_name = faker.person.fullName(); 
      const username = faker.internet.username();
      const password = faker.internet.password();
      
      const query = `
          INSERT INTO client (full_name, username, password)
          VALUES ($1, $2, $3);
      `;
      const values = [full_name, username, password];
      try {
          await client.query(query, values);
          console.log(`Inserted client ${i + 1}`);
      } catch (err) {
          console.error('Error inserting client:', err.stack);
      }
  }
}

async function insertData() {
    try {
        await insertStores();
        await insertCategories();
        await insertProducts();
        await insertClients();
        await client.end();
    } catch (err) {
        console.error('Error during data insertion:', err.stack);
        await client.end();
    }
}

client.connect()
    .then(() => console.log('Connected to the database'))
    .catch(err => console.error('Connection error', err.stack))
    .finally(() => insertData());
