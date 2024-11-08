const { faker } = require('faker');
const { Pool } = require('pg');

// Configuration de la connexion à la base de données
const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: 'i_commerce',
  password: '1234',
  port: 5432,
});

async function createFakeData() {
  try {
    // Suppression préalable des tables si elles existent déjà
    await Promise.all([
      pool.query('DROP TABLE IF EXISTS store CASCADE'),
      pool.query('DROP TABLE IF EXISTS category CASCADE'),
      pool.query('DROP TABLE IF EXISTS product CASCADE')
    ]);

    // Création des tables
    await pool.query(`
      CREATE TABLE store (
        id_store SERIAL PRIMARY KEY,
        title VARCHAR(50) NOT NULL,
        location VARCHAR(50),
        pub VARCHAR(50)
      );
    `);

    await pool.query(`
      CREATE TABLE category (
        id_category SERIAL PRIMARY KEY,
        store INTEGER REFERENCES store(id_store),
        category_name VARCHAR(50) NOT NULL UNIQUE,
        description VARCHAR(50),
        unity VARCHAR(50)
      );
    `);

    await pool.query(`
      CREATE TABLE product (
        id_product SERIAL PRIMARY KEY,
        category INTEGER REFERENCES category(id_category),
        price DOUBLE PRECISION,
        sizes VARCHAR(50),
        color VARCHAR(20)
      );
    `);

    // Insertion des données fictives pour store
    const stores = [];
    for (let i = 0; i < 50; i++) {
      stores.push({
        title: faker.company.companyName(),
        location: `${faker.address.city()}, ${faker.address.stateAbbr()} ${faker.random.number({min: 1000, max: 9999})}`,
        pub: faker.random.arrayElement(['Oui', 'Non'])
      });
    }
    await pool.query('INSERT INTO store (title, location, pub) VALUES $1', [stores]);

    // Insertion des données fictives pour category
    const categories = [];
    for (let i = 0; i < 150; i++) { // 3 catégories par magasin
      categories.push({
        store: faker.random.number({min: 1, max: 50}),
        category_name: faker.commerce.department(),
        description: faker.lorem.paragraph(),
        unity: faker.random.arrayElement(['kg', 'm²', 'unités'])
      });
    }
    await pool.query('INSERT INTO category (store, category_name, description, unity) VALUES $1', [categories]);

    // Insertion des données fictives pour product
    const products = [];
    for (let i = 0; i < 200; i++) { // 4 produits par catégorie
      products.push({
        category: faker.random.number({min: 1, max: 150}),
        price: faker.finance.amount(5, 500, 2),
        sizes: faker.lorem.word(),
        color: faker.color.human()
      });
    }
    await pool.query('INSERT INTO product (category, price, sizes, color) VALUES $1', [products]);

    console.log('Données fictives créées avec succès !');
  } catch (error) {
    console.error('Erreur lors de la création des données fictives:', error);
  } finally {
    pool.end();
  }
}

createFakeData();
