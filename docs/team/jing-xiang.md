# Jing Xiang - Project Portfolio Page

## Overview

LongAh! is a CLI-based application designed to help users track debts within friend groups and determine the
least transaction method of settling these debts. It is optimized for busy people with large transaction quantities
among friends. It is written in Java.


### Summary of Contributions

Given below are my contributions to the project.

- **New Feature**: Added the `add` command to allow users to add new debts to the application.
  - What it does: This feature allows users to add new debts to the application, specifying the debtor, creditor, and amount.
  - Justification: This feature is the basis of the application as it allows users to input their debts into the application.
  - Highlights: This enhancement impacts both existing and future commands, necessitating a thorough analysis of design alternatives. The implementation was challenging due to the required changes to existing commands and input formats.

- **New Feature**: Added the `edit` command to allow users to edit existing debts in the application.
  - What it does: This feature allows users to edit existing debts in the application, changing the debtor, creditor, or amount.
  - Justification: This feature improves the product significantly by enabling users to update information without having to delete and re-enter entries.
  - Highlights: This feature extends the functionality of the application, requiring a deep understanding of the existing codebase and the ability to integrate new features seamlessly.

- **New Feature** Added the `pin` command to implement PIN authentication for the application.
  - What it does: This feature allows users to set, reset, enable and disable a PIN for the application, which must be created upon the first start up.
  - Justification: This feature enhances the security of the application, ensuring that only authorized users can access the application.
  - Highlights: This feature required a thorough understanding of the existing codebase and the ability to integrate new features seamlessly. It also involved implementing a secure and user-friendly authentication system using hashing algorithms. It also required storage of the PIN in a secure manner.

- **New Feature** Added the `chart` command to allow users to view a graphical representation of the debts in the application.
  - What it does: This feature allows users to view a bar chart of the debts in the application, showing the distribution of debts among friends.
  - Justification: This feature enhances the user experience by providing a visual representation of the debts, making it easier for users to understand the data.
  - Highlights: This feature required integrating a third-party library for chart generation and implementing a parser to convert the data into a format suitable for the library.
  - Credits: The implementation of this feature was supported by [XChart](https://knowm.org/open-source/xchart/), an open-source library for chart generation.


- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=jing-xiang&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


- **Project management**:
  - Managed and conducted releases ```v1.0``` - ```v2.1``` (3 releases) on GitHub
  - Managed feature increments and frequent bug fixing on GitHub Issues [#78](https://github.com/AY2324S2-CS2113-T15-1/tp/issues/78), [#96](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/96), [#91](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/91), [#117](https://github.com/AY2324S2-CS2113-T15-1/tp/issues/117) [#164](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/164)

- **Enhancements to existing features**:
    - Improved the `list` command to display debts in a more user-friendly format.
    - Enhanced the `pin` command to allow for more seamless PIN authentication and management. [#67](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/67)
    - Wrote additional tests for the above features. [#86](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/86)

- **Documentation**:
- User Guide:
  - Added documentation for the features `pin`, and `chart`. [#87](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/87)
- Developer Guide:
  - Added implementation details and sequence diagram of the features `pin`, and `chart`.  [#74](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/74), [#73](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/73)
  - Added UML diagram [#90](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/90) [#173](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/173)

- **Community**:
- PRs reviewed (with non-trivial review comments): examples [#89](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/89), [#80](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/80), [#77](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/77), [#76](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/76), [#43](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/43)
- Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/nus-cs2113-AY2324S2/tp/pull/63), [2](https://github.com/nus-cs2113-AY2324S2/tp/pull/1), [3](https://github.com/nus-cs2113-AY2324S2/tp/pull/13)).
- Contributed to forum posts (examples: [1](https://github.com/nus-cs2113-AY2324S2/forum/issues/14), [2](https://github.com/nus-cs2113-AY2324S2/forum/issues/28)).

- **Tools**:
  - Integrated the `XChart` library for the `chart` feature [#86](https://github.com/AY2324S2-CS2113-T15-1/tp/pull/86).