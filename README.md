# 🖥️ UI Automation Framework (Work In Progress)

This repository contains a **UI Test Automation Framework** built using **Selenium WebDriver**, **TestNG**, and the **Page Object Model (POM)** design pattern.  
It demonstrates support for **multiple environments**, **cross‑browser testing**, **suite/group level execution**, **parallel test execution**

The framework uses [The Internet Herokuapp](https://the-internet.herokuapp.com/) as the target application — a practice site that provides a wide range of UI operations commonly encountered in web automation.

---

## 🎯 Key Features

- **Multi‑Environment Support** → Run tests seamlessly across **DEV / SIT / UAT**.  
- **Cross‑Browser Testing** → Execute tests on Chrome, Firefox, Edge, etc.  
- **Suite/Group Level Execution** → Control test execution via TestNG XML configuration.  
- **Parallel Test Execution** → Achieved using **TestNG** and **ThreadLocal WebDriver** for thread‑safe browser instances.  
- **CI/CD Ready** → Supports CLI execution and integration with Jenkins/GitHub Actions.  
- **Reporting** → Generates detailed HTML reports using **Extent Reports**.

---

## 🛠️ Tech Stack

- **Automation Tool:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Design Pattern:** Page Object Model (POM)  
- **Build Tool:** Maven  
- **Utilities & Libraries:**
  - Apache POI → Excel test data management
  - Custom helper utilities → Browser actions, waits, validations

---

## ⚙️ Configurations

- Test execution is controlled via **TestNG XML files**.  
- Parameters such as:
  - `environmentToUse` → Selects environment (DEV / SIT / UAT).  
  - `browserToUse` → Selects browser (Chrome, Firefox, Edge, etc).  
- This setup allows the same scripts to run across different environments and browsers without code changes.

---

## 📊 Test Data Strategy

- Certain tests use **DataProviders** to read input from **Excel sheets**.  
- Supports **multiple run conditions** for data‑driven testing.  
- Ensures flexibility and scalability in test execution.

---

## 🧩 Framework Design

- **Page Object Model (POM):** Encapsulates UI elements and actions for reusability.  
- **TestNG XML:** Defines suites, groups, and execution flow.  
- **Parallel Execution:** Implemented with **ThreadLocal WebDriver**, ensuring isolated browser sessions per thread.  
- **CLI Support:** Tests can be triggered via command line for CI/CD pipelines.  
- **Assertions:** Validate UI behavior against expected outcomes.

---

## 📑 Reporting

- **Extent Reports** → Generates rich HTML reports with detailed logs, screenshots, and execution status.  
- Easy to integrate with CI/CD pipelines for publishing results.

---


## 🚧 Roadmap

- Extend coverage to additional modules/pages.  
- Enhance reporting.

---

