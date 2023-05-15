Feature: Rest API Functionalities

  Scenario: An author can add or a delete a book
#    Given There is an author
    When A user creates an author
    Then The author is created
    When The user updates the author
    Then The author is updated
    When The user deletes the author
    Then The author is deleted
    When The user creates a book for a single author
    Then The book is created
    When The user gets a book belonging to a single author
    Then The book is retrieved
    When The user wants to update a book belonging to a single author
    Then The book is updated
    When The user deletes a book belonging to a single author
    Then The book is deleted

