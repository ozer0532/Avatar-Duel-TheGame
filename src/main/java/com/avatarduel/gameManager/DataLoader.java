package com.avatarduel.gamemanager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;

import com.avatarduel.card.Aura;
import com.avatarduel.card.Card;
import com.avatarduel.card.Char;
import com.avatarduel.card.Destroy;
import com.avatarduel.card.Land;
import com.avatarduel.card.PowerUp;
import com.avatarduel.model.Element;
import com.avatarduel.player.Player;
import com.avatarduel.util.CSVReader;

public class DataLoader {
    static final String LAND_CSV_FILE_PATH = "/com/avatarduel/card/data/land.csv";
    static final String CHAR_CSV_FILE_PATH = "/com/avatarduel/card/data/character.csv";
    static final String AURA_CSV_FILE_PATH = "/com/avatarduel/card/data/skill_aura.csv";
    static final String PWRU_CSV_FILE_PATH = "/com/avatarduel/card/data/skill_powerup.csv";
    static final String DSTR_CSV_FILE_PATH = "/com/avatarduel/card/data/skill_destroy.csv";
    static final String P1_CSV_FILE_PATH = "/com/avatarduel/card/data/player1.csv";
    static final String P2_CSV_FILE_PATH = "/com/avatarduel/card/data/player2.csv";
    
    public void LoadCards(Player player1, Player player2) throws IOException, URISyntaxException, NumberFormatException {
        
        // List of cards each player have
        List<Card> p1Cards = new LinkedList<>();
        List<Card> p2Cards = new LinkedList<>();

        // Get all cards data
        List<String[]> landRows = getCSVData(LAND_CSV_FILE_PATH);
        List<String[]> charRows = getCSVData(CHAR_CSV_FILE_PATH);
        List<String[]> auraRows = getCSVData(AURA_CSV_FILE_PATH);
        List<String[]> pwruRows = getCSVData(PWRU_CSV_FILE_PATH);
        List<String[]> dstrRows = getCSVData(DSTR_CSV_FILE_PATH);

        // Get deck contents
        List<String[]> p1Rows = getCSVData(P1_CSV_FILE_PATH);
        // Randomize deck insert
        for (String[] row : p1Rows) {
            insertCard(p1Cards, row[0], landRows, charRows, auraRows, pwruRows, dstrRows);
        }
        if (p1Rows.size() > 60 || p1Rows.size() < 40) {
            // throw not enough cards exception
        }
        // Push all data into stack
        Stack<Card> p1Deck = player1.getPlayerDeck();
        for (Card card : p1Cards) {
            p1Deck.push(card);
        }
        
        System.out.println("----------------------------- 0 ------------------------------");
        // Get deck contents
        List<String[]> p2Rows = getCSVData(P2_CSV_FILE_PATH);
        // Randomize deck insert
        for (String[] row : p2Rows) {
            insertCard(p2Cards, row[0], landRows, charRows, auraRows, pwruRows, dstrRows);
        }
        if (p2Rows.size() > 60 || p2Rows.size() < 40) {
            // throw not enough cards exception
        }
        // Push all data into stack
        Stack<Card> p2Deck = player2.getPlayerDeck();
        for (Card card : p2Cards) {
            p2Deck.push(card);
        }
    }

    private void insertCard(List<Card> cardsList, String card, List<String[]> landRows, List<String[]> charRows, 
            List<String[]> auraRows, List<String[]> pwruRows, List<String[]> dstrRows) {
        Random random = new Random();
        String[] cardAttributes = cardsInList(landRows, card);
        if (cardAttributes != null) {
            cardsList.add(random.nextInt(cardsList.size() + 1), 
                    new Land(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], cardAttributes[4]));
            return;
        }
        cardAttributes = cardsInList(charRows, card);
        if (cardAttributes != null) {
            cardsList.add(random.nextInt(cardsList.size() + 1), 
            new Char(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], cardAttributes[4],
            Integer.parseInt(cardAttributes[7]), Integer.parseInt(cardAttributes[5]), Integer.parseInt(cardAttributes[6])));
            return;
        }
        cardAttributes = cardsInList(auraRows, card);
        if (cardAttributes != null) {
            cardsList.add(random.nextInt(cardsList.size() + 1), 
            new Aura(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], cardAttributes[4],
            Integer.parseInt(cardAttributes[5]), Integer.parseInt(cardAttributes[6]), Integer.parseInt(cardAttributes[7])));
            return;
        }
        cardAttributes = cardsInList(dstrRows, card);
        if (cardAttributes != null) {
            cardsList.add(random.nextInt(cardsList.size() + 1), 
            new Destroy(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], cardAttributes[4], Integer.parseInt(cardAttributes[5])));
            return;
        }
        cardAttributes = cardsInList(pwruRows, card);
        if (cardAttributes != null) {
            cardsList.add(random.nextInt(cardsList.size() + 1), 
            new PowerUp(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], cardAttributes[4], Integer.parseInt(cardAttributes[5])));
            return;
        }
        // System.out.println("----------------------------- 1 ------------------------------");
        // throw card not found error
    }
    
    private String[] cardsInList (List<String[]> list, String index) {
        Optional<String[]> strings = list.stream()
        .filter(s -> s[0].equals(index))
        .findFirst();
        if (strings.isPresent()) {
            System.out.println("Loading card: " + strings.get()[1]);
            return strings.get();
        } else {
            return null;
        }
    }

    private List<String[]> getCSVData(String pathFile) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(pathFile).toURI());
        CSVReader csvReader = new CSVReader(csvFile, "\t");
        csvReader.setSkipHeader(true);
        return csvReader.read();
    }
}