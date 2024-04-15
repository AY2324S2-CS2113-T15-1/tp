# Sim Justin - Project Portfolio Page

## Overview

LongAh! is a CLI-based application designed to help users track debts within friend groups and determine the least transaction method of settling these debts. It is optimized for busy people with large transaction quantities among friends. It is written in Java.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Member Handling
  * What it does: Representation of each member object, with name and balance as attributes and methods to get, set or modify each of the attributes.
  * Justification: Encapsulation of member-related data and behavior to promote code organization and modularity.
  * Highlights: Incorporates validation checks to ensure the integrity of member data, enhancing reliability and consistency.

* **New Feature**: Transaction Solving Algorithm
  * What it does: This feature implements an algorithm to balance transactions between members by creating subtransactions.
  * Justification: This algorithm efficiently distributes debts and credits among members, minimizing discrepancies and simplifying financial reconciliation.
  * Highlights: Handles cases where one member owes multiple others or is owed by multiple others, ensuring all debts and credits are properly accounted for. Optimizes transaction processing by minimizing the number of subtransactions required while maintaining accuracy.

* **New Feature**: Added Data Storage Capability.
  * What it does: This feature introduces the ability to store member and transaction data for a group in persistent files. It includes methods for loading data from files into memory and saving data from memory to files.
  * Justification: Storage capability is essential for preserving data between program executions. By storing data in files, users can resume their work and maintain a record of their transactions even after closing the application. This feature enhances the usability and practicality of the application.
  * Highlights: Creates subdirectories for each discrete group unit.

* **General Contributions**: Abstraction of Commands, Exceptions and Logging
  * What it does
    * *Commands Abstraction*: Encapsulates command execution logic into a base class `Command`, providing a standardized interface for all commands in the system.
    * *Exception Abstraction*: Centralizes exception handling logic, allowing for consistent error reporting and graceful error recovery across the application. It includes methods for printing exception messages to the user interface and logging them based on severity level.
    * *Logging Abstraction*: Encapsulation of logging logic for consistent logging across the application.
  * Justification: Abstraction of commands, exceptions, and logging enhances the overall structure and maintainability of the codebase. It promotes separation of concerns, making it easier to manage and extend different aspects of the application independently.

* **General Contributions**: Improve text UI testing
  * What it does
    * Carry out automated testing as if under use by a normal user. Simulates multiple use sessions.
  * Justification: Helps to catch bugs that may show up over the course of a normal use case.
  * Impact
    * Identified and rectified bugs relating to double representation in storage and list commands. [#160](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/160)

* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=1simjustin&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**
  * User Guide
    * Updated command reference. [#98](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/98)
    * Paginated v2.0 UG. [#101](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/101), [#102](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/102)
    * Updated application expected behaviour as of v2.0. [#153](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/153)
  * Developer Guide
    * Added user stories and glossary. [#70](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/70)
    * Added implementation details for StorageHandler, Exception and Logging. Added instructions for testing. [#71](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/71)
    * Added inheritance diagram for `Command`. [#92](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/92)
    * Updated overall class diagram. [#154](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/154)
    * Added high-level flowchart. [#155](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/155)
    * Added section on `UI and I/O`. [#155](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/155)
    * Added section on `Member and MemberList`. [#155](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/155)

<div style="page-break-after: always;"></div>

* **Project Management**
  * Maintained issues and managed milestones.
  * In charge of issues assignee allocation.
  
* **Community**
  * PRs reviewed (with non-trivial review comments): [#31](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/31), [#32](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/32), [#40](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/40), [#49](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/49), [#53](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/53), [#55](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/55), [#61](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/61), [#77](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/77), [#86](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/86), [#89](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/89)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/1simjustin/ped/issues/1), [2](https://github.com/1simjustin/ped/issues/2), [3](https://github.com/1simjustin/ped/issues/6))
