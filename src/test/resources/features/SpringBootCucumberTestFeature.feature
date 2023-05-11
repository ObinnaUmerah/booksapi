Feature: Rest API Functionalities

  Scenario: An author can add or a delete a book
    Given There is an author
    When An author publishes a book
    Then Check if the book is published
    When The author wants to revise his book
    Then Release new copies of that book
    When The author now wants to delete the original copy
    Then The book is no longer available for purchase

