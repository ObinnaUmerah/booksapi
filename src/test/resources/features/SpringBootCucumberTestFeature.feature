Feature: Rest API Functionalities

  Scenario: An author can add or a delete a book
#    Given There is an author
    When An author publishes a book
    Then Check if the book is published
    When The author wants to revise his book
    Then Release new copies of that book
#    When The author now wants to delete the original copy
#    Then The book is no longer available for purchase
    When An author writes a book
    Then The book is written
    When The user wants to retrieve an author's book
    Then The user has access to the author's book
    When The user wants to edit the author's book
    Then The author's book is edited
    When The user removes the author's book from his library
    Then The book is removed

