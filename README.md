A simple Blackjack game implemented in Java featuring the following key components:

Deck Management: A deck of cards is shuffled and dealt to both the player and the dealer.
Card Values & Ace Handling: The game correctly handles the value of Aces, treating them as 11 unless the player would bust, in which case they are counted as 1.
Game Flow: The player is prompted to hit or stand, with the option to play again after each round.
Dealer Rules: The dealer follows standard Blackjack rules, drawing cards until reaching at least 17.
Winner Determination: After the dealer stands, the totals are compared to determine the winner.

Features:
Card dealing, drawing, and value calculation
Dynamic Ace handling to prevent busting
Player decisions: Hit or Stand
Dealer logic for automatic drawing
Option to play multiple rounds

How to Play:
The game deals two cards to both the player and the dealer.
The player can choose to Hit (get another card) or Stand (keep their current total).
The dealer then reveals their second card and draws until their total reaches at least 17.
If the player's total exceeds 21, they bust. If the dealer’s total exceeds 21, the player wins.
The player wins if their total is closer to 21 than the dealer’s, without exceeding 21.
