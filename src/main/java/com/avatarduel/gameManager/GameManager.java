// GameManager.java
package com.avatarduel.gameManager;

import com.avatarduel.card.*;
import com.avatarduel.gameState.*;
import com.avatarduel.model.Element;
import com.avatarduel.player.*;
import com.avatarduel.sprite.*;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class GameManager {
    // Atribut-atribut dari GameManager
    private Player currentPlayer;
    private Player oppositePlayer;
    private List<IMouseClickSub> mouseClickSubs;
    private List<IMouseMoveSub> mouseMoveSubs;
    private GameDrawer gameDrawer;
    private GraphicsContext graphicsContext;

    // Constructor
    public GameManager(GraphicsContext gc){
        // Init gameDrawer
        graphicsContext = gc;
        gameDrawer = new GameDrawer();
        
        // Init CurrentPlayer dan oppositePlayer
        currentPlayer = new Player(false);
        oppositePlayer = new Player(true);
        
        // Generate kartu dan masukin ke player yang bersangkutan, dan simpan ke drawListnya GameDrawer
        // Init exit button dan masukin spritenya ke drawlistnya gamedrawer
        // Masukin 7 kartu ke tangan kedua player
        // Init state dengan draw phase
    }
    
    // Public Method untuk GameManager
    public void gameLoop(double durationSinceLastFrame){
        // Ini dipanggil oleh Avatar Duel pada Animation Timer pada handle()
        // Reference: (1) - Cari bagian animation (bisa dipake buat yang bukan animasi juga)
        this.gameDrawer.drawGame(this, durationSinceLastFrame);
    }

    public void switchPlayer(){
        // Menukar giliran player dalam bermain
        Player temp = this.currentPlayer;
        this.currentPlayer = this.oppositePlayer;
        this.oppositePlayer = temp;
    }
    
    public Card getClickedCardFromHand(int X, int Y){
        // Cek kartu apa yang di klik dari tangan player saat itu
        // Return null kalo gak ada kartu yang di klik
        // Caranya loop untuk setiap kartu di hand kalo Sprite.OverlapPoint
        for (Card kartu : this.currentPlayer.getPlayerHands()){
            if (kartu.getSprite().isPointOverlap(X, Y)){
                return kartu;
            }
        }
        return null;
    }

    public ArenaClickInfo getArenaClickInfo(int X, int Y){
        // Cek posisi klik dari mouse, apakah ada di belah atas atau bawah, dan ada di kolom ke berapa
        // Cek apakah ada kartu di posisi tersebut
        // Return ArenaClickInfo
        for (int i = 0; i < 8; i++){
            Card kartu = this.currentPlayer.getPlayerArena().getCharCard()[i];
            if (kartu != null){
                if (kartu.getSprite().isPointOverlap(X, Y)){
                    return new ArenaClickInfo(kartu, true, this.currentPlayer.getIsTopPlayer(), i, true, false);
                }
            }
        }
        for (int i = 0; i < 8; i++){
            Card kartu = this.currentPlayer.getPlayerArena().getSkills()[i];
            if (kartu != null){
                if (kartu.getSprite().isPointOverlap(X, Y)){
                    return new ArenaClickInfo(kartu, false, this.currentPlayer.getIsTopPlayer(), i, false, true);
                }
            }
        }
        return null;
    }

    // Setter untuk GameManager
    public void setCurrentPlayer(Player P){
        this.currentPlayer = P;
    }

    public void setOppositePlayer(Player P){
        this.oppositePlayer = P;
    }

    public void setGameDrawer(GameDrawer gd){
        this.gameDrawer = gd;
    }

    public void setMouseClickSubs(List<IMouseClickSub> mcSubs){
        this.mouseClickSubs = mcSubs;
    }

    public void setMouseMoveSubs(List<IMouseMoveSub> mmSubs){
        this.mouseMoveSubs = mmSubs;
    }

    public void setGraphicsContext(GraphicsContext gc){
        this.graphicsContext = gc;
    }

    
    // Getter untuk GameManager
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public Player getOppositePlayer(){
        return this.oppositePlayer;
    }

    public List<IMouseClickSub> getMouseClickSubs(){
        return this.mouseClickSubs;
    }

    public List<IMouseMoveSub> getMouseMoveSubs(){
        return this.mouseMoveSubs;
    }
    
    public GameDrawer getGameDrawer(){
        return this.gameDrawer;
    }
    
    public GraphicsContext getGraphicsContext(){
        return this.graphicsContext;
    }
    
    // Subscriber-Publisher Method
    public void RegisterMouseClick(IMouseClickSub click){
        // Mendaftarkan IMouseClickSub agar dikirim event nanti
        mouseClickSubs.add(click);
    }

    public void RegisterMouseMove(IMouseMoveSub move){
        // Mendaftarkan IMouseClickSub agar dikirim event nanti
        mouseMoveSubs.add(move);
    }

    public void sendMouseClickEvent(MouseEvent event){
        // Kirim event OnMouseClick ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse click
        for (int i = 0; i < mouseClickSubs.size(); i++){
            mouseClickSubs.get(i).OnMouseClick(event);
        }
    }

    public void sendMouseMoveEvent(MouseEvent event){
        // Kirim event OnMouseMove ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse move
        for (int i = 0; i < mouseMoveSubs.size(); i++){
            mouseMoveSubs.get(i).OnMouseMove(event);
        }
    }

    private void LoadCards() throws IOException, URISyntaxException, NumberFormatException {
        final String LAND_CSV_FILE_PATH = "card/data/land.csv";
        final String CHAR_CSV_FILE_PATH = "card/data/character.csv";
        final String AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
        final String P1_CSV_FILE_PATH = "card/data/player1.csv";
        final String P2_CSV_FILE_PATH = "card/data/player2.csv";
        
        List<Card> p1Cards = new LinkedList<>();
        List<Card> p2Cards = new LinkedList<>();

        File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
        CSVReader landReader = new CSVReader(landCSVFile, "\t");
        landReader.setSkipHeader(true);
        List<String[]> landRows = landReader.read();
        
        File charCSVFile = new File(getClass().getResource(CHAR_CSV_FILE_PATH).toURI());
        CSVReader charReader = new CSVReader(charCSVFile, "\t");
        charReader.setSkipHeader(true);
        List<String[]> charRows = charReader.read();
        
        File auraCSVFile = new File(getClass().getResource(AURA_CSV_FILE_PATH).toURI());
        CSVReader auraReader = new CSVReader(auraCSVFile, "\t");
        auraReader.setSkipHeader(true);
        List<String[]> auraRows = auraReader.read();

        File p1CSVFile = new File(getClass().getResource(P1_CSV_FILE_PATH).toURI());
        CSVReader p1Reader = new CSVReader(p1CSVFile, "\t");
        p1Reader.setSkipHeader(true);
        List<String[]> p1Rows = p1Reader.read();
        for (String[] row : p1Rows) {
            addCards(p1Cards, row[0], landRows, charRows, auraRows);
        }
        if (p1Rows.size() > 60 || p1Rows.size() < 40) {
            // throw not enough cards exception
        }
        Stack<Card> p1Deck = currentPlayer.getPlayerDeck();
        for (Card card : p1Cards) {
            p1Deck.push(card);
        }

        File p2CSVFile = new File(getClass().getResource(P2_CSV_FILE_PATH).toURI());
        CSVReader p2Reader = new CSVReader(p2CSVFile, "\t");
        p2Reader.setSkipHeader(true);
        List<String[]> p2Rows = p2Reader.read();
        for (String[] row : p2Rows) {
            addCards(p2Cards, row[0], landRows, charRows, auraRows);
        }
        if (p2Rows.size() > 60 || p2Rows.size() < 40) {
            // throw not enough cards exception
        }
        Stack<Card> p2Deck = oppositePlayer.getPlayerDeck();
        for (Card card : p2Cards) {
            p2Deck.push(card);
        }
    }

    private void addCards(List<Card> cardsList, String card, List<String[]> landRows, List<String[]> charRows, List<String[]> auraRows) {
        Random random = new Random();
        // if (card == "PA") {
        //     cardsList.add(random.nextInt(cardsList.size()), new PowerUp("Power Up", Element.AIR, "Power Up", sprite, 2));
        //     return;
        // }
        // if (card == "PE") {
        //     cardsList.add(random.nextInt(cardsList.size()), new PowerUp("Power Up", Element.EARTH, "Power Up", sprite, 2));
        //     return;
        // }
        // if (card == "PF") {
        //     cardsList.add(random.nextInt(cardsList.size()), new PowerUp("Power Up", Element.FIRE, "Power Up", sprite, 2));
        //     return;
        // }
        // if (card == "PW") {
        //     cardsList.add(random.nextInt(cardsList.size()), new PowerUp("Power Up", Element.WATER, "Power Up", sprite, 2));
        //     return;
        // }
        // if (card == "DA") {
        //     cardsList.add(random.nextInt(cardsList.size()), new Destroy("Destroy", Element.AIR, "Destory", sprite, 2));
        //     return;
        // }
        // if (card == "DE") {
        //     cardsList.add(random.nextInt(cardsList.size()), new Destroy("Destroy", Element.EARTH, "Destory", sprite, 2));
        //     return;
        // }
        // if (card == "DF") {
        //     cardsList.add(random.nextInt(cardsList.size()), new Destroy("Destroy", Element.FIRE, "Destory", sprite, 2));
        //     return;
        // }
        // if (card == "DW") {
        //     cardsList.add(random.nextInt(cardsList.size()), new Destroy("Destroy", Element.WATER, "Destory", sprite, 2));
        //     return;
        // }
        String[] cardAttributes = cardsInList(landRows, card);
        if (cardAttributes != null) {
            // cardsList.add(random.nextInt(cardsList.size()), 
            //         new Land(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3]));
            return;
        }
        cardAttributes = cardsInList(charRows, card);
        if (cardAttributes != null) {
            // cardsList.add(random.nextInt(cardsList.size()), 
            //         new Char(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], 
            //                 Integer.parseInt(cardAttributes[7]), Integer.parseInt(cardAttributes[5]), Integer.parseInt(cardAttributes[6]));
            return;
        }
        cardAttributes = cardsInList(auraRows, card);
        if (cardAttributes != null) {
            // cardsList.add(random.nextInt(cardsList.size()), 
            //         new Aura(cardAttributes[1], Element.valueOf(cardAttributes[2]), cardAttributes[3], 
            //                 Integer.parseInt(cardAttributes[5]), Integer.parseInt(cardAttributes[6]), Integer.parseInt(cardAttributes[7]));
            return;
        }
        // throw card not found error
    }

    private String[] cardsInList (List<String[]> list, String index) {
        Optional<String[]> strings = list.stream()
                .filter(s -> s[0] == index)
                .findFirst();
        if (strings.isPresent()) {
            return strings.get();
        } else {
            return null;
        }
    }
}