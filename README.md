# 🚀 Workflow Automation Test Framework

[![CI/CD Pipeline](https://shields.io)](https://github.com)
[![Live Dashboard](https://shields.io)](https://github.io)

This project is a comprehensive automated testing framework designed to validate software quality from end to end. All library configurations, dependencies, test frameworks, Cucumber Runtime, and Allure configurations are centrally managed within a single build management file: `build.gradle`.

---

## 📊 Test Report Dashboards & Report Links
All test execution results are fully automated via the CI/CD pipeline and stored securely within the `gh-pages` branch. You can access the main dashboard and detailed execution reports through the following links:

* **🔗 Main Dashboard:** [Dashboard Test Report Result](https://github.io)
* **🗂️ Report Repository:** [GitHub Pages Branch Source](https://github.com)

### 🔬 Test Execution Reports Breakdown by Layer

| Testing Category | Cucumber Report | Allure Report |
| :--- | :--- | :--- |
| **⚙️ API Testing** | [Cucumber API Test Report](https://github.ioapi/api_test_report.html) | [Allure API Test Report](https://github.ioallure-api/) |
| **🌐 Web UI Testing** | [Cucumber Web Test Report](https://github.ioapi/webUi_test_report.html) | [Allure Web Test Report](https://github.ioallure-web/) |

---

## 🛠️ Technology Stack & Test Environment Specifications

| Component Attribute | API Testing Layer | Web UI Testing Layer |
| :--- | :--- | :--- |
| **Language** | Java | Java |
| **Target URL** | [dummyapi.io](https://dummyapi.io) | [demoblaze.com](https://demoblaze.com) |
| **Class Name** | `TestRunnerApi` | `TestRunnerWeb` |
| **Testing Framework** | JUnit | JUnit |
| **BDD Framework** | Cucumber | Cucumber |
| **Build Tool** | Gradle | Gradle |
| **Testing Library** | RestAssured | Selenium WebDriver |
| **Assertions Library** | AssertJ (`assertj-core`) | AssertJ (`assertj-core`) |
| **Reporting Library** | Cucumber Report & Allure Report | Cucumber Report & Allure Report |

---

## 🏗️ Development Lifecycle & Framework Architecture

The development of this framework follows industry best practices by implementing the following structured phases:

### 1. Test Scenario Design (BDD Gherkin)
Test cases are written using the Gherkin syntax in `.feature` files under the `resources` directory, covering both positive and negative scenarios:
* **API Test:** `api.feature`
* **Web UI Test:**
  * `signup.feature` ➔ Validates the user registration flow (*signup*).
  * `login.feature` ➔ Validates the user authentication flow (*login*).
  * `logout.feature` ➔ Validates the user session termination flow (*logout*).
  * `product.feature` ➔ Validates product catalog browsing and cart management (*browsing & add to cart*).
  * `payment.feature` ➔ Validates payment form submission and order completion (*payment/checkout*).
  * `e2e_purchase.feature` ➔ Validates the complete end-to-end purchasing flow (Login ➔ Select Product ➔ Checkout ➔ Logout).

### 2. Step Definitions Implementation
Acts as a bridge to translate Gherkin plain-text steps into programmatic page object actions:
* **API Testing Suite:**
  * `ApiStepdefs.java` ➔ Contains step implementations for the `api.feature` file.
* **Web UI Testing Suite:**
  * `CommonSteps.java` ➔ Houses generic actions such as button clicks and basic page navigation.
  * `AuthSteps.java` ➔ Manages SignUp, Login, and Logout flows along with their corresponding validations.
  * `ProductSteps.java` ➔ Handles product catalog management, item selection, and add-to-cart operations.
  * `CheckoutSteps.java` ➔ Controls the end-to-end checkout processing and payment validation logic.

### 3. Page Object Model (POM) Components
Strict separation of user interface elements/endpoints from test logic to ensure the codebase remains highly maintainable:
* **API Testing POM:**
  * `ApiBasePage.java` ➔ Base class handling HTTP requests (GET, POST, PUT, DELETE).
  * `TagApiPage.java` ➔ Action and element mappings for the `/tag` endpoint.
  * `UserApiPage.java` ➔ Action and element mappings for the `/user` endpoint.
* **Web UI Testing POM:**
  * `HomePage.java` ➔ Element mappings and user interactions for the primary landing page.
  * `LoginPage.java` ➔ Interactivity mapping for the login form interface.
  * `SignUpPage.java` ➔ Interactivity mapping for the registration form interface.
  * `ProductPage.java` ➔ Interactivity mapping for target details on specific product items.
  * `CartPage.java` ➔ Management of user interactions inside the shopping cart section.
  * `CheckoutPage.java` ➔ Controls checkout data form submission up to final purchase confirmation.

### 4. Test Lifecycle & Runners (Hooks & Runners)
* **Test Hooks (`APIHooks.java` & `WebHooks.java`):** Provides `@Before` and `@After` annotations to handle environment setup and tear-down actions, keeping test executions clean, isolated, and flaky-free.
* **Test Runners (`TestRunnerApi.java` & `TestRunnerWeb.java`):** Serves as the core engine connecting BDD `.feature` files with step definitions, executing test suites, and triggering test report generation.

### 5. Configuration & Utilities Management (Utils)
* **`config.properties`:** External properties file containing global environment settings like `baseURL`, browser preferences, and global explicit/implicit timeout thresholds.
* **Helper UI Utilities:**
  * `DriverFactory.java` ➔ Manages central instantiations of the WebDriver (Chrome/Firefox) and sets driver bin paths.
  * `ConfigReader.java` ➔ Responsible for parsing and extracting environmental values from `config.properties`.
  * `WaitHelper.java` ➔ Supplies explicit and implicit wait mechanisms to guarantee UI synchronization, preventing random test failures caused by page latency (*flaky tests*).

---

## ⚙️ Continuous Integration (CI/CD Pipeline)

Test execution is completely automated via **GitHub Actions** workflows managed by the `FinalAutomationTest.yml` file under the `.github/workflows` folder:

1. **Automated Triggers:** Any new code push or pull request to the `main` branch automatically triggers the execution of the entire test suite.
2. **Headless Execution:** Web UI automated tests run within an Ubuntu runner utilizing **Headless Chrome** (Chrome browser executing tasks without rendering a graphical user interface).
3. **Artifact Deployment:** Once the pipeline completes, test data artifacts are parsed, translated into Allure/Cucumber HTML format, and pushed to the `gh-pages` branch to update the live test reporting dashboard dynamically.

---

## 🎛️ Additional Environment Controls (Optional)
* **`.gitattributes`:** Configured to accurately normalize file paths, balance the repository language presentation metrics, and mitigate noise warnings during test runs on localized shells.
