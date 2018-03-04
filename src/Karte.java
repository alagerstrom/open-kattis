import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Karte {

    private enum Suit {
        P,
        K,
        H,
        T
    }

    private static class Card {
        public Suit suit;
        public int number;

        public Card() {
        }

        public Card(Suit suit, int number) {
            this.suit = suit;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Card card = (Card) o;
            return number == card.number &&
                    suit == card.suit;
        }

        @Override
        public int hashCode() {
            return Objects.hash(suit, number);
        }

        @Override
        public String toString() {
            return "Card{" +
                    "suit=" + suit +
                    ", number=" + number +
                    '}';
        }
    }

    private class CardExistsException extends Exception {

    }

    private class Deck {
        Card[] p = new Card[14];
        Card[] k = new Card[14];
        Card[] h = new Card[14];
        Card[] t = new Card[14];

        public void insert(Card card) throws CardExistsException {
            switch (card.suit) {
                case P:
                    insert(card, p);
                    break;
                case K:
                    insert(card, k);
                    break;
                case H:
                    insert(card, h);
                    break;
                case T:
                    insert(card, t);
                    break;
                default:
                    throw new RuntimeException("Could not insert card, unknown suit");
            }
        }

        private void insert(Card card, Card[] pile) throws CardExistsException {
            if (pile[card.number] != null)
                throw new CardExistsException();
            pile[card.number] = card;
        }

        int getMissingCards(Card[] pile) {
            int missingCards = 13;
            for (int i = 1; i < pile.length; i++) {
                if (pile[i] != null)
                    missingCards--;
            }
            return missingCards;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < line.length(); i += 3) {
            String s = line.substring(i, i + 3);
            cards.add(parseCard(s));
        }
        new Karte().solve(cards);
    }

    private static Card parseCard(String s) {
        Suit suit;
        if (s.startsWith(Suit.H.name()))
            suit = Suit.H;
        else if (s.startsWith(Suit.K.name()))
            suit = Suit.K;
        else if (s.startsWith(Suit.P.name()))
            suit = Suit.P;
        else
            suit = Suit.T;
        int value = Integer.parseInt(s.substring(1, 3));
        return new Card(suit, value);
    }

    private void solve(List<Card> cards) {
        Deck deck = new Deck();
        for (Card card : cards) {
            try {
                deck.insert(card);
            } catch (CardExistsException e) {
                printError();
                return;
            }
        }
        System.out.println(
                deck.getMissingCards(deck.p) + " " +
                        deck.getMissingCards(deck.k) + " " +
                        deck.getMissingCards(deck.h) + " " +
                        deck.getMissingCards(deck.t)
        );
    }

    private void printError() {
        System.out.println("GRESKA");
    }
}
