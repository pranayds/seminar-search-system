# Seminar Search System

A Java-based seminar scheduling and search application featuring multi-index architecture and spatial queries. This system efficiently manages seminar records through multiple Binary Search Trees (BSTs) and a 2D spatial tree, enabling fast lookups across different attributes.

## Features

- **Multi-Index Architecture**: Maintains separate BST indexes for ID, cost, date, and keywords
- **Spatial Search**: 2D Binary Tree (BinTree) implementation for radius-based location queries
- **Range Queries**: Efficient search within cost and date ranges
- **Exact Match Search**: Fast lookups by ID or keyword
- **Consistent Updates**: Maintains data integrity across all indexes during insertions and deletions
- **Command-Driven Interface**: File-based command processing for batch operations

## Technical Highlights

### Data Structures Implemented

1. **Binary Search Tree (BST)** - Generic implementation supporting:
   - Configurable duplicate key handling
   - In-order, range, and exact-match searches
   - Efficient insertion and deletion with tree balancing

2. **2D Spatial Binary Tree (BinTree)** - Spatial indexing with:
   - Flyweight pattern for empty nodes
   - Radius-based search optimization
   - Dynamic node splitting based on coordinate overlap

3. **Sorted Linked List** - Used within leaf nodes for handling seminars at identical coordinates

### Architecture

```
SeminarDB
├── BST<Integer, Seminar> idTree       (unique IDs)
├── BST<Integer, Seminar> costTree     (allows duplicates)
├── BST<String, Seminar> dateTree      (allows duplicates)
├── BST<String, Seminar> keywordTree   (allows duplicates)
└── BinTree binTree                     (spatial indexing)
```

## Tech Stack

- **Language**: Java
- **Data Structures**: Binary Search Trees, 2D Spatial Trees, Linked Lists
- **Design Patterns**: Flyweight (EmptyNode), Command Pattern (CommandProcessor)
- **Testing**: JUnit

## Building and Running

### Prerequisites
- Java JDK 8 or higher
- JUnit library (included in `lib/student.jar`)

### Compile
```bash
javac -cp "lib/student.jar" -d bin src/*.java
```

### Run
```bash
java -cp "bin:lib/student.jar" SemSearch <world-size> <command-file>
```

**Parameters:**
- `world-size`: Integer defining the coordinate space (e.g., 1024 for a 1024x1024 world)
- `command-file`: Path to file containing commands

### Example Commands

```
insert 1
Overview of HCI Research at VT
0610051600 90 10 10 45
HCI Computer_Science VT Virginia_Tech
This seminar will present an overview of HCI research at VT

search ID 1
search keyword VT
search cost 0 100
search date 0500000000 1000000000
search location 10 10 50

delete 1

print ID
print cost
print keyword
print date
print location
```

## Project Structure

```
seminar-search-system/
├── src/
│   ├── SemSearch.java           # Main entry point
│   ├── SeminarDB.java           # Database coordinator
│   ├── CommandProcessor.java   # Command parser
│   ├── Seminar.java             # Data model
│   ├── BST.java                 # Generic BST implementation
│   ├── BinTree.java             # 2D spatial tree
│   └── SortedLinkedList.java   # Linked list for leaf nodes
├── lib/
│   └── student.jar              # Testing library
└── bin/                         # Compiled classes
```

## Key Algorithms

### Spatial Search (Radius Query)
- Uses bounding box optimization to prune search space
- Alternates between x and y coordinate splits at each tree level
- O(log n + k) average case, where k is the number of results

### Range Search
- Leverages BST ordering to skip irrelevant subtrees
- Tracks nodes visited for performance analysis
- O(log n + k) for balanced trees

### Multi-Index Consistency
- All insert/delete operations update all relevant indexes atomically
- Prevents orphaned records or index inconsistencies

## Performance Characteristics

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Insert | O(log n) × 5 indexes | O(n) |
| Delete | O(log n) × 5 indexes | O(1) |
| Search by ID | O(log n) | O(1) |
| Range Search | O(log n + k) | O(1) |
| Spatial Search | O(log n + k) | O(1) |

*n = number of seminars, k = number of results*

## Author

Pranay Dhalwani
