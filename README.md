# Poker Chip Ecosystem

## Kevin Hu - CPSC 210 Term Project

I will be creating a **virtual poker chip counter** that
stimulates how poker chips are used in a real poker ecosystem.
This includes players having a starting chip balance,
adding their chips into the pot, raising, checking, etc.
When a person wins their hand, all of the chips in the pot get
transferred into the winners chip balance. To clarify - I am
simply designing the chip economy itself, and there will be no
playing cards or poker game mechanics involved (such as
distributing cards, or analyzing winning hands).

This application will be used by people who want to play poker
in real life with their friends, but have no poker chips!
Playing cards are very accessible and cheap, but chips are not.
Poker chips are heavy to bring around (so you are only limited
to playing at home), and expensive to buy.

This idea was inspired from when I travelled to The Philippines
with my friends, and we had a long layover at the airport with
nothing but playing cards. We wanted to play poker but did not
have any chips to simulate the betting system (the fun part of poker).

### *User Stories*:

- As a user, I want to be able to add a new player into the poker game
- As a user, I want to be able to remove a player from the poker game
- As a user, I want to be able to view each player's chip balance
- As a user, I want to be able to add chips into the pot
- As a user, I want to be able to claim all the winnings from the pot
- As a user, I want to be able to view pot balance
- As a user, I want to be able to save my poker game (players, balances, pot)
- As a user, I want to be able to be able to load my poker game from file

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by
  entering a player name (string) and player balance (integer) and clicking the
  "Add Player" button, which will add X - the player to Y - the game. This is
  information is displayed in the JList.
- You can generate the second required event related to adding Xs to a Y by
  clicking an existing player in the JList and removing them from the game by
  clicking the "Remove Player" button.
  "Add Player" button, which will add X - the player to Y - the game. This is
  information is displayed in the JList.
- You can locate my visual component by selecting a player and pressing the
  "Claim Pot" button when the pot balance is > 0. There will be a visual displayed
  indicating the jackpot winner. This visual will be removed whenever the "Make Bet"
  pressed again.
- You can save the state of my application by clicking "Exit Game", as it will
  automatically save all the player names and corresponding balances.
- You can reload the state of my application by clicking "Load Game", and all the
  players and corresponding balances will reappear inside the JList.
 