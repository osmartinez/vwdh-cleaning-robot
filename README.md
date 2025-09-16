# Cleaning Robot - DDD + Hexagonal Architecture

## Overview

This project implements a simple "cleaning robot" in Kotlin using Test-Driven Development (TDD), Domain-Driven Design (DDD), and a Hexagonal (Ports & Adapters) architecture.

## What the Program Does

- The floor is a rectangular grid
- A robot has a position (x, y) and an orientation (N, E, S, W)
- The robot responds to a sequence of instructions:
    - **L**: turn left
    - **R**: turn right
    - **M**: move forward one cell
- Moving outside the floor throws a domain error
- A simple CLI reads input, executes the instructions, logs start/end states, and stores the final robot state in-memory

## Key Decisions

- **Technology Stack**: Kotlin + Gradle + JDK 21
- **Methodology**: TDD first
- **Architecture**: DDD with entities, value objects, domain events, and domain exceptions. The project structure could have been partitioned by modules, each containing its own application, domain, and infrastructure layers. However, since this is a small problem, I decided to structure it from the outside in, using application, domain, and infrastructure as the main folders.
- **Pattern**: Hexagonal architecture

Disclaimer: This project is not intended to be feature-complete. The goal is to demonstrate development and design techniques rather than to deliver a complete application with many functionalities.

## Repository Structure

```
src/
├── main/
│   ├── kotlin/
│   │   ├── application/
│   │   │   └── RunInstructionsToRobot.kt
│   │   ├── domain/
│   │   │   ├── Floor.kt
│   │   │   ├── Instruction.kt
│   │   │   ├── Orientation.kt
│   │   │   ├── Position.kt
│   │   │   ├── Robot.kt
│   │   │   ├── events/
│   │   │   │   ├── DomainEvent.kt
│   │   │   │   ├── RobotEvent.kt
│   │   │   │   └── RobotMoved.kt
│   │   │   ├── exceptions/
│   │   │   │   └── DomainException.kt
│   │   │   └── ports/
│   │   │       ├── in/
│   │   │       │   └── RunInstructionsToRobot.kt
│   │   │       └── out/
│   │   │           ├── DomainLogger.kt
│   │   │           └── RobotStateRepository.kt
│   │   └── infrastructure/
│   │       └── adapters/
│   │           ├── in/
│   │           │   ├── cli/
│   │           │   │   ├── Cli.kt
│   │           │   │   └── CliParser.kt
│   │           │   └── http/
│   │           └── out/
│   │               ├── InMemoryRobotStateRepository.kt
│   │               └── StdoutLogger.kt
│   └── resources/
└── test/
    ├── kotlin/
    │   ├── acceptance/
    │   │   └── SampleAcceptanceTest.kt
    │   ├── application/
    │   │   └── RunInstructionsToRobotTest.kt
    │   ├── domain/
    │   │   ├── InstructionTest.kt
    │   │   ├── OrientationTest.kt
    │   │   ├── PositionTest.kt
    │   │   ├── RobotTest.kt
    │   │   └── events/
    │   │       └── RobotMovedTest.kt
    │   └── infrastructure/
    │       └── adapters/
    │           └── in/
    │               └── cli/
    │                   └── CliParserTest.kt
    └── resources/
```

### Domain Layer (`src/main/kotlin/domain`)

Core model and logic, pure Kotlin (no I/O):

- **Core Objects**: Floor, Position, Orientation, Robot, Instruction, InstructionList
- **Events**: Domain events for robot movements and state changes
- **Errors**: Domain-specific exceptions for business rule violations
- **Ports**: Interfaces defining contracts between layers

### Application Layer (`src/main/kotlin/application`)

Orchestrates use cases using domain objects and ports:

- **Use Case**: `RunInstructionsToRobot.kt` - Main application service that coordinates robot instruction execution

### Infrastructure Layer (`src/main/kotlin/infrastructure`)

Adapters that communicate with the outside world:

#### Input Adapters
- **CLI**: Command-line interface for robot instruction input
- **HTTP**: Placeholder for future REST API support

#### Output Adapters
- **Repository**: In-memory storage for robot state
- **Logger**: Standard output logging implementation

### Test Structure (`src/test/kotlin`)

Comprehensive test suite following TDD principles:

- **Domain Tests**: Unit tests for core business logic
- **Application Tests**: Integration tests
- **Infrastructure Tests**: Test for cli adapter implementations
- **Acceptance Tests**: End-to-end behavioral test

## Architecture Flow

### How the Layers Communicate

1. **Domain Layer**: Pure logic with no printing, database, or network operations
2. **Application Layer (Use Case)**: Coordinates domain operations and depends on domain output ports
3. **Infrastructure Layer**: Implements the ports and provides the adapters (CLI, loggers, repositories)

### CLI Flow

1. CLI reads text lines from STDIN
2. CliParser parses: Floor size and a list of (Robot initial state + Instructions)
3. Application service `RunInstructionsToRobot` executes instructions for each robot, logs, and persists final state via ports
4. StdoutLogger prints log lines; InMemoryRobotStateRepository stores state in memory

## Domain-Driven Design Highlights

### Core Concepts

- **Entity**: Robot (has identity RobotId, mutable state via pure functions returning copies)
- **Value Objects**: Position, Orientation, Floor (immutable inputs with behavior)
- **Domain Events**: RobotMoved(id, from, to, happenedAt, id) - Robot collects events when moving
- **Domain Exception**: Prevents illegal moves (e.g., moving outside the floor)


## Build, Run, and Test

### Prerequisites

- **JDK 21** (required)
- **Kotlin/Gradle**: No need to install; use `./gradlew`

### Common Commands

```bash
# Clean and build
./gradlew clean build

# Run all tests
./gradlew test
```

### Running the CLI

#### Direct Execution

```bash
./gradlew clean build
echo "5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM" | ./gradlew runCli --quiet
```

#### Input Format Explanation

- **Floor size**: `5 5` (coordinates 0..5 for both x and y inclusive)
- **First robot**: At position `(1, 2)` facing North, execute `LMLMLMLMM`
- **Second robot**: At position `(3, 3)` facing East, execute `MMRMMRMRRM`

#### Running with Input Files

**macOS/Linux:**
```bash
cat input.txt | ./gradlew runCli
```

**Windows (PowerShell):**
```bash
Get-Content input.txt | ./gradlew.bat runCli
```

### Expected Output

The program logs using StdoutLogger:

```
BUILD SUCCESSFUL in 1s
5 actionable tasks: 5 executed
[i] Starting position: (1, 2)
[i] Final position: (1, 3)
[i] Final orientation: N
[i] Starting position: (3, 3)
[i] Final position: (5, 1)
[i] Final orientation: E
```

## Code Reading Guide

Follow this suggested path for understanding the codebase:

1. **`domain/Orientation`** and **`domain/Position`**
2. **`domain/Instruction`** and **`InstructionList`** - How commands are parsed
3. **`domain/Robot`** - Entity behavior, domain events
4. **`application/RunInstructionsToRobot`**
5. **`infrastructure/adapters/in/cli`** - Input parsing and entry point
6. **Tests** - Follow the same path in `src/test`

## Future Work

### Data Transfer Objects (DTOs)
- **Layer Decoupling**: Replace direct domain object exposure with DTOs to maintain clean boundaries between layers
- **Mapping Strategy**: Implement converter/mapper services to transform between domain objects and DTOs bidirectionally

### Floor Aggregate Enhancement
- Make the floor more scalable, supporting multiple scenarios with:
    - Obstacles
    - Machines
    - Walls
    - Restricted areas

### Robot Aggregate Extension
- **Battery Management**:
    - Battery levels tracking
    - Charging mechanisms
- **Risk Detection**:
    - Blocked path detection
    - Unsafe zone identification

### Domain Events System
- Define additional domain events:
    - `RobotTurned`
    - `BatteryLevelChanged`
    - `IncidentRaised`
- Complete the event publishing mechanism with a proper event bus

