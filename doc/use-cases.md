## LibMan API: Example Use Case Scenarios
Below is a list of use cases organized by role. The system handles actions differently based on the user (authorization).

> [!NOTE]
> Some of these use cases may not be immediately applicable to the API. I'll be working to filter or simplify them to fit what exactly I want to get out of this project.

## Admins and Librarians
1. Add a new book to the catalog, including title, author, genre, and number of available copies.
2. Remove a damaged or outdated book from the system.
3. Register a new library user (e.g., a patron who wants to borrow books).
4. Update information about a book, such as correcting a typo in the author’s name or increasing stock.
5. Suspend a user's borrowing privileges due to unpaid fines or misconduct.
6. View all currently borrowed books, including overdue ones.
7. Record the return of a borrowed book and calculate any late fees.

## Registered Users (Patrons)
1. Search for a book by title, author, or genre.
2. Borrow a book if available and within allowed borrowing limits.
3. Check which books they currently have checked out and when they are due.
4. Return a book either on time or late.
5. Extend the borrowing period if the book hasn’t been requested by someone else.
6. Receive notifications (email or internal) when:
   - A due date is approaching.
   - A reserved book becomes available.
7. Reserve a book that is currently checked out by someone else.

## System and API
1. Prevent duplicate book entries when adding new items to the catalog.
2. Enforce borrow limits (e.g., only 5 books per user at a time).
3. Track borrowing history for each user for analytics or user dashboards.
4. Calculate late fees automatically based on the return date.
5. Ensure role-based access control, e.g., only librarians can add or remove books.
6. Prevent borrowing of unavailable or already borrowed books.
7. Log actions like borrow/return for audit or reports.