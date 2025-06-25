# React SSR with Spring Boot and GraalVM

This project demonstrates how to integrate server-side rendering (SSR) of React components in a Spring Boot backend using GraalVM's JavaScript engine.

---

## 📁 Project Structure

```
ssr-springboot-react-graalvm/
├── frontend/           # React app compiled to SSR bundle
├── src/                # Spring Boot backend source code
├── pom.xml             # Maven configuration
└── README.md           # This file
```

---

## 🚀 Requirements

- **Java 21+ (GraalVM recommended)**
- **Maven 3.6+**
- **Node.js 18+**
- (Optional) **GraalVM** with JavaScript support (`js` component)

---

## 🛠 Setup

### 1. Clone the repository

```bash
git clone https://github.com/your-username/ssr-springboot-react-graalvm.git
cd ssr-springboot-react-graalvm
```

---

### 2. Build the frontend (React)

```bash
cd frontend
npm install
npm run build
```

> This will create a `ssr-bundle.js` file in `frontend/build/`.

---

### 3. Build and run the backend

```bash
cd ..
mvn spring-boot:run
```

> The app runs on: [http://localhost:8080/render?component=App&props={"name":"Ahmad"}](http://localhost:8080/render?component=App&props=%7B%22name%22%3A%22Ahmad%22%7D)

---

## 🧠 How It Works

- The React app is bundled with `ReactDOMServer` to support server-side rendering.
- Spring Boot uses GraalVM’s JavaScript engine to evaluate the SSR bundle.
- The `/render` endpoint takes a React component name and JSON props to render it on the server.

---

## 💡 Example Request

```http
GET /render?component=App&props={"name":"Ahmad"}
```

Returns the HTML string rendered by `<App name="Ahmad" />`.

---

## ⚙️ Optional: Run with GraalVM

If you're using **GraalVM**, make sure to enable JavaScript support:

```bash
gu install js
```

Then run the app with the GraalVM `java` binary to benefit from native JS interop.

---

## 📄 License

MIT
