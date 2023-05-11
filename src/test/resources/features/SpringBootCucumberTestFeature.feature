Feature: Rest API Functionalities

  Scenario: An author can add or a delete a book
    Given There is an author
    When An author publishes a book
    Then Check if the book is published
    When The author wants to revise his book
    Then Release new copies of that book
#    Given A list of books are available
#    When The aut hor removes a book

