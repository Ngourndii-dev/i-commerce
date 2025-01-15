import faker from "@faker-js/faker"
// Générer 30 magasins (store)
function generateStores() {
  const stores = [];
  for (let i = 0; i < 30; i++) {
    stores.push({
      title: faker.company.companyName(),
      location: faker.address.city(),
      pub: faker.company.catchPhrase()
    });
  }
  return stores;
}

// Générer 30 catégories (category)
function generateCategories(stores) {
  const categories = [];
  for (let i = 0; i < 30; i++) {
    const storeId = faker.random.arrayElement(stores).id_store;
    categories.push({
      store: storeId,
      category_name: faker.commerce.department(),
      description: faker.lorem.sentence(),
      unity: faker.commerce.productMaterial()
    });
  }
  return categories;
}

// Générer 30 produits (product)
function generateProducts(categories) {
  const products = [];
  for (let i = 0; i < 30; i++) {
    const categoryId = faker.random.arrayElement(categories).id_category;
    products.push({
      category: categoryId,
      price: faker.commerce.price(),
      sizes: faker.random.arrayElement(['S', 'M', 'L', 'XL']),
      color: faker.commerce.color()
    });
  }
  return products;
}

// Générer 30 clients (client)
function generateClients() {
  const clients = [];
  for (let i = 0; i < 30; i++) {
    clients.push({
      full_name: faker.name.findName(),
      username: faker.internet.userName(),
      password: faker.internet.password()
    });
  }
  return clients;
}

// Générer les insertions SQL
function generateInsertSQL() {
  const stores = generateStores();
  const categories = generateCategories(stores);
  const products = generateProducts(categories);
  const clients = generateClients();

  let sql = '';

  // Insertions pour la table store
  stores.forEach(store => {
    sql += `INSERT INTO store (title, location, pub) VALUES ('${store.title}', '${store.location}', '${store.pub}');\n`;
  });

  // Insertions pour la table category
  categories.forEach(category => {
    sql += `INSERT INTO category (store, category_name, description, unity) VALUES (${category.store}, '${category.category_name}', '${category.description}', '${category.unity}');\n`;
  });

  // Insertions pour la table product
  products.forEach(product => {
    sql += `INSERT INTO product (category, price, sizes, color) VALUES (${product.category}, ${product.price}, '${product.sizes}', '${product.color}');\n`;
  });

  // Insertions pour la table client
  clients.forEach(client => {
    sql += `INSERT INTO client (full_name, username, password) VALUES ('${client.full_name}', '${client.username}', '${client.password}');\n`;
  });

  return sql;
}

// Afficher les insertions SQL
console.log(generateInsertSQL());
