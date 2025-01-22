
# Blockchain Simulation - Spring Boot Application

This project simulates the core functionalities of a blockchain using Spring Boot. It implements basic blockchain operations such as adding blocks, mining, validating the chain, and retrieving the complete chain. The project also includes API documentation using Swagger for easy interaction with the REST APIs.

## Features

- Add new blocks to the blockchain with transaction data
- Mine new blocks (proof of work)
- Validate the blockchain's integrity
- Retrieve the entire blockchain
- API documentation available via Swagger

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (for persistence)
- PostgreSQL (for database storage)
- Swagger/OpenAPI (for API documentation)

## API Endpoints

### 1. Add Block

#### **POST /blockchain/add**
Adds a new block to the blockchain with the provided transaction data.

**Request Parameters:**

- `transactions` (required): Transaction data in JSON format.

**Example Request:**

```bash
POST http://localhost:8080/blockchain/add?transactions={"sender":"JohnDoe","receiver":"JaneSmith","amount":200.00,"timestamp":"2025-01-22T10:00:00Z","currency":"USD"}
```

**Response:**

```json
{
    "index": 7,
    "timestamp": "2025-01-22T09:00:23.389272Z",
    "transactions": "{\"sender\":\"JohnDoe\",\"receiver\":\"JaneSmith\",\"amount\":200.00,\"timestamp\":\"2025-01-22T10:00:00Z\",\"currency\":\"USD\"}",
    "previousHash": "0000a183e1b7c0e3c0bf64f97d2502f261bc4e23d0772e3bcd9382e4612056aa",
    "currentHash": "0000a21ff70dbff693208009d03ca3203526652bce23b99675e9c20abc7193fd",
    "nonce": 157580
}
```

### 2. Validate Blockchain

#### **GET /blockchain/validate**
Validates the integrity of the blockchain to check if it has been tampered with.

**Response:**

```json
true
```

### 3. Get Blockchain

#### **GET /blockchain/chain**
Retrieves the full list of blocks in the blockchain.

**Response:**

```json
[
    {
        "index": 1,
        "timestamp": "2025-01-22T09:00:23.389272Z",
        "transactions": "Genesis Block",
        "previousHash": "0000000000000000000000000000000000000000000000000000000000000000",
        "currentHash": "0000a183e1b7c0e3c0bf64f97d2502f261bc4e23d0772e3bcd9382e4612056aa",
        "nonce": 157580
    },
    {
        "index": 2,
        "timestamp": "2025-01-22T09:10:23.399272Z",
        "transactions": "{\"sender\":\"JohnDoe\",\"receiver\":\"JaneSmith\",\"amount\":200.00,\"timestamp\":\"2025-01-22T10:00:00Z\",\"currency\":\"USD\"}",
        "previousHash": "0000a183e1b7c0e3c0bf64f97d2502f261bc4e23d0772e3bcd9382e4612056aa",
        "currentHash": "0000a21ff70dbff693208009d03ca3203526652bce23b99675e9c20abc7193fd",
        "nonce": 157590
    }
]
```

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- PostgreSQL (or other database if configured)
- An IDE (e.g., IntelliJ IDEA, Eclipse)

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/blockchain-simulation.git
   ```

2. Configure PostgreSQL connection in `application.properties` (located in `src/main/resources`):

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/blockchain
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Install the required dependencies:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

### Swagger UI

Once the application is running, you can access Swagger UI to interact with the API:

[http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

### Testing the APIs

You can test the API endpoints using Postman, cURL, or directly from Swagger UI.

#### cURL Command for Testing:

To add a new block with a sample transaction:

```bash
curl -X POST "http://localhost:8080/blockchain/add?transactions=%7B%22sender%22%3A%22JohnDoe%22%2C%22receiver%22%3A%22JaneSmith%22%2C%22amount%22%3A200.00%2C%22timestamp%22%3A%222025-01-22T10%3A00%3A00Z%22%2C%22currency%22%3A%22USD%22%7D"
```

To retrieve the blockchain:

```bash
curl -X GET "http://localhost:8080/blockchain/chain"
```

## License

This project is open source, licensed under the MIT License.

## Contact

For more details, feel free to reach out to **Karan Sahani** at **karansahani723@gmail.com**.

