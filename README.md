# Visual Java Editor

Visual Java Editor is a lightweight Swing-based Java code editor, developed as part of an OOM Mini Project.

## Key Features

### Syntax Highlighting and Modern Editor Tools

- Utilizes RSyntaxTextArea for syntax highlighting and code folding.

### Theme Integration

- Leverages FlatLaf for a lightweight, modern, and customizable look and feel.

### File Management

- Provides options for creating, opening, saving, and saving files as new files.
- Integrated file dialogs simplify file operations.

### Compile and Run in Terminal

- Allows direct compilation and execution of Java files through the system terminal, supporting Windows, macOS, and Linux terminals.
- Automatically saves code before compiling to avoid data loss.

## Getting Started

### Prerequisites

1. **Java Development Kit (JDK)**: Ensure JDK 21 or higher version is installed.
2. **Maven**: Required for dependency management and project builds.

### Build and Run the Project

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA or any other IDE of your choice.
3. If Maven dependencies are not downloaded automatically, run the following command:
   ```bash
   mvn clean install
   ```
4. Run the `JavaEditor` main class from the IDE's run configuration.
5. The application will launch, ready for use.

### Running Java Code

1. Open the Visual Java Editor application.
2. Write your Java code in the editor pane.
3. Save the file using options in the **File** menu.
4. Compile and execute the program through the **Run** menu, which launches the system terminal.

## Project Overview

### Core Components

- **JavaEditor.java**: The main class responsible for initializing the application and setting up the user interface.
- **ExecutionManager.java**: Manages the compilation and execution processes of Java files.
- **EditorPane.java**: Extends RSyntaxTextArea to deliver syntax highlighting and modern editor functionalities.
- **FileManager.java**: Handles file operations such as opening, saving, and creating files.
- **ThemeManager.java**: Configures themes using FlatLaf.

### Dependencies

This project uses the following libraries:

- **[RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea)**: Adds syntax highlighting and advanced editor features.
- **[FlatLaf](https://www.formdev.com/flatlaf/)**: Offers a modern look and feel for Swing applications.
