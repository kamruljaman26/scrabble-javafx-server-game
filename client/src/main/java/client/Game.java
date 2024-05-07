/**
 * Manjil Pradhan
 * This class represents the entry point of the game. It handles all the
 * necessary logic to play the game.
 *//*


package client;

import client.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    private static int brdSize;
    private static Board board;
    private static Dictonary dict;
    private static TrieNode root;
    private static Map<Pos, List<Character>> crossCheckResult = new HashMap<>();
    private static PrintTile printTile = new PrintTile();
    private static String direction = "";
    private static List<Character> trayLetters;
    private static List<Character> copyTrayLetters;
    private static List<Character> copyCopyTrayLetters;
    private static int count;


    public static void main(String[] args) throws FileNotFoundException {
        File boardFile = new File("resources/example_input.txt");
        Scanner scan = new Scanner(boardFile);
        brdSize = Integer.valueOf(scan.nextLine());
        board = new Board(brdSize);

        // maybe init values
        String[] boardConf = new String[brdSize + 1];
        int index = 0;
        while (scan.hasNextLine()){
            boardConf[index] = scan.nextLine();
            index++;
        }

        for (int i = 0; i < boardConf.length - 1; i++){
            String parts[] = boardConf[i].split("\\s+");
            int j = 0;
            for (String part : parts){
                if (part.length() == 0) continue;
                Tile tile = new Tile();
                tile.setValue(part);
                if (part.length() == 1){
                    tile.setData(part.charAt(0));
                }else if (part.charAt(0) == '3'){
                    tile.setMultiplier(Board.MULTIPLIER.TW);
                    tile.setData('\0');
                }else if(part.charAt(0) == '2'){
                    tile.setMultiplier(Board.MULTIPLIER.DW);
                    tile.setData('\0');
                }else if (part.length() > 1 && part.charAt(1) == '3'){
                    tile.setMultiplier(Board.MULTIPLIER.TL);
                    tile.setData('\0');
                } else if (part.length() > 1 && part.charAt(1) == '2'){
                    tile.setMultiplier(Board.MULTIPLIER.DL);
                    tile.setData('\0');
                }else{
                    tile.setMultiplier(Board.MULTIPLIER.NO);
                    tile.setData('\0');
                }
                tile.setPos(new Pos(i, j));
                board.setTile(tile, i, j);
                j++;
            }
        }

        char[] tray = boardConf[boardConf.length - 1].toCharArray();
        trayLetters = new ArrayList<>();
        copyTrayLetters = new ArrayList<>();
        copyCopyTrayLetters = new ArrayList<>();
        for (int i = 0; i < tray.length; i++){
            trayLetters.add(tray[i]);
            copyTrayLetters.add(tray[i]);
            copyCopyTrayLetters.add(tray[i]);
        }

        dict = new Dictonary(args[0]);
        root = dict.makeTree();

        findAllWords(trayLetters);

        System.out.println("Solution " + printTile.getWord() + " " +
                "has " + printTile.getTotalScore() + " points.");
        System.out.println("Solution Board:");
        System.out.println(board);

    }

    */
/**
     * Given the position, it returns the before position
     * @param pos
     * @return left position
     *//*

    public static Pos before(Pos pos){
        Pos sendPos = null;
        if (direction.equals("across")){
            sendPos = new Pos(pos.getX(), pos.getY() - 1);
        }else{
            sendPos = new Pos(pos.getX() - 1, pos.getY());
        }
        return sendPos;
    }

    */
/**
     * Given the position, it returns the after position
     * @param pos
     * @return before position
     *//*

    public static Pos after(Pos pos){
        Pos sendPos = null;
        if (direction.equals("across")){
            sendPos = new Pos(pos.getX(), pos.getY() + 1);
        }else{
            sendPos = new Pos(pos.getX() + 1, pos.getY());
        }
        return sendPos;
    }

    */
/**
     * Given the position, it returns the left and upper position.
     * It is helpful in finding cross checks.
     * @param pos
     * @return left position
     *//*

    public static Pos beforeCross(Pos pos){
        Pos sendPos = null;
        if (direction.equals("across")){
            sendPos = new Pos(pos.getX() - 1, pos.getY());
        }else{
            sendPos = new Pos(pos.getX(), pos.getY() - 1);
        }
        return sendPos;
    }

    */
/**
     * Given the position, it returns the right and down position.
     * It is helpful in finding cross checks.
     * @param pos
     * @return left position
     *//*

    public static Pos afterCross(Pos pos){
        Pos sendPos = null;
        if (direction.equals("across")){
            sendPos = new Pos(pos.getX() + 1, pos.getY());
        }else{
            sendPos = new Pos(pos.getX(), pos.getY() - 1);
        }
        return sendPos;
    }

    */
/**
     * This functions finds the corrdinate of all the anchors from where
     * words can be played.
     * @param givenBoard
     * @return
     *//*

    public static List<Pos> findAnchor(Board givenBoard){
        List<Pos> anchors =  new ArrayList<>();
        for (int i = 0; i < brdSize; i++){
            for (int j = 0; j < brdSize; j++){
                if (givenBoard.isFilled(board, i, j)){
                    if (i > 0 && !givenBoard.isFilled(board, i - 1, j) &&
                            !(anchorContainsPos(anchors, new Pos(i - 1, j)))){
                        anchors.add(new Pos(i - 1, j));
                    }
                    if (i < brdSize - 1 && !givenBoard.isFilled(board, i + 1, j) &&
                            !(anchorContainsPos(anchors, new Pos(i + 1, j)))){
                        anchors.add(new Pos(i + 1, j));
                    }
                    if (j > 0 && !givenBoard.isFilled(board, i, j - 1) &&
                            !(anchorContainsPos(anchors, new Pos(i, j - 1)))){
                        anchors.add(new Pos(i, j - 1));
                    }
                    if (j < brdSize - 1 && !givenBoard.isFilled(board, i, j + 1) &&
                            !(anchorContainsPos(anchors, new Pos(i, j + 1)))){
                        anchors.add(new Pos(i, j + 1));
                    }
                }
            }
        }
        return anchors;
    }

    */
/**
     * This function is used to find the list of all characters that can be played
     * at a given position. If a word contains a letter that is not valid in given
     * position, then that word cannot be played. It is helpful for cross checking
     * to check if the word can be played or not.
     * @return Map of position and list of characters.
     *//*

    public static Map<Pos, List<Character>> crossChecks(){
        Map<Pos, List<Character>> result = new HashMap<>();
        for (Pos pos : board.validPosition()){
            if (board.isFilled(board, pos)){
                continue;
            }
            String lettersBefore = "";
            Pos scanPos = pos;
            while (board.tileInBound(beforeCross(scanPos)) &&
                    board.isFilled(board, beforeCross(scanPos))){
                scanPos = beforeCross(scanPos);
                lettersBefore = board.getTile(scanPos).getData() + lettersBefore;
            }
            String lettersAfter = "";
            scanPos = pos;
            while (board.tileInBound(afterCross(scanPos)) &&
                    board.isFilled(board, afterCross(scanPos))){
                scanPos = afterCross(scanPos);
                lettersAfter = lettersAfter + board.getTile(scanPos).getData();
            }
            List<Character> legalHere = new ArrayList<>();
            if (lettersBefore.length() == 0 && lettersAfter.length() == 0) {
                for (char start = 'a'; start <= 'z'; start++){
                    legalHere.add(start);
                }
            }else{
                for (char letter : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
                    String wordFormed = lettersBefore + letter + lettersAfter;
                    if (root.isWord(wordFormed)){
                        legalHere.add(letter);
                    }
                }
            }
            result.put(pos, legalHere);
        }
        return  result;
    }

    */
/**
     * This function finds the highest scoring word by using printTile object.
     * It compares the score of each word and saves the highest scoring word.
     * @param word
     * @param lastPos
     *//*

    public static void legal_move(String word, Pos lastPos){
        int max = printTile.getTotalScore();
        int currentScore = getLettersScore(word, lastPos);
        if (currentScore > max){
            max = currentScore;
            printTile.setWord(word);
            printTile.setPos(lastPos);
            printTile.setDirection(direction);
            printTile.setTotalScore(max);
        }
    }

    */
/**
     *This function
     * @param word
     * @param lastPos
     * @return
     *//*

    public static int getLettersScore(String word, Pos lastPos){
        int[] letterScore = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        HashMap<Character, Integer> scoreMap = new HashMap<>();
        Pos checkPos = after(lastPos);
        int j = 0;
        for (char start = 'a'; start <= 'z'; start++){
            scoreMap.put(start, letterScore[j]);
            scoreMap.put(Character.toUpperCase(start), letterScore[j]);
            j++;
        }
        int score = 0;
        count = 0;
        int crossBonus = 0;

        Board.MULTIPLIER bonus = null;
        for (int i = word.length() - 1; i >= 0; i--){
            checkPos = before(checkPos);
            if (board.getTile(checkPos).getMultiplier() == Board.MULTIPLIER.DL){
                score += (2 * scoreMap.get(word.charAt(i)));
                if (board.isFilled(board, afterCross(checkPos))
                        || board.isFilled(board, beforeCross(checkPos)))
                    crossBonus += (2 * scoreMap.get(word.charAt(i)));
            }else if (board.getTile(checkPos).getMultiplier() == Board.MULTIPLIER.DW){
                score += scoreMap.get(word.charAt(i));
                bonus = Board.MULTIPLIER.DW;
                if (board.isFilled(board, afterCross(checkPos))
                        || board.isFilled(board, beforeCross(checkPos)))
                    crossBonus += scoreMap.get(word.charAt(i));
            }else if (board.getTile(checkPos).getMultiplier() == Board.MULTIPLIER.TL){
                score += 3 * scoreMap.get(word.charAt(i));
                if (board.isFilled(board, afterCross(checkPos))
                        || board.isFilled(board, beforeCross(checkPos)))
                    crossBonus += 3 * scoreMap.get(word.charAt(i));
            }else if(board.getTile(checkPos).getMultiplier() == Board.MULTIPLIER.TW){
                score += scoreMap.get(word.charAt(i));
                bonus = Board.MULTIPLIER.TW;
                if (board.isFilled(board, afterCross(checkPos))
                        || board.isFilled(board, beforeCross(checkPos)))
                    crossBonus += scoreMap.get(word.charAt(i));
            }else{
                score += scoreMap.get(word.charAt(i));
                if (board.isFilled(board, afterCross(checkPos))
                        || board.isFilled(board, beforeCross(checkPos)))
                    crossBonus += scoreMap.get(word.charAt(i));
            }

            Pos crossCheckPos = checkPos;
            while (board.tileInBound(afterCross(crossCheckPos))
                    && board.isFilled(board, afterCross(crossCheckPos))){
                crossCheckPos = afterCross(crossCheckPos);
                crossBonus += scoreMap.get(board.getTile(crossCheckPos).getData());
            }
            crossCheckPos = checkPos;
            while (board.tileInBound(beforeCross(crossCheckPos))
                    && board.isFilled(board, beforeCross(crossCheckPos))){
                crossCheckPos = beforeCross(crossCheckPos);
                crossBonus += scoreMap.get(board.getTile(crossCheckPos).getData());
            }

            if (copyTrayLetters.contains(word.charAt(i))
                    && board.getTile(checkPos).getData() != word.charAt(i)){
                copyTrayLetters.remove(Character.valueOf(word.charAt(i)));
                count++;
            }

        }
        if(bonus == Board.MULTIPLIER.DW){
            score *= 2;
        }
        if (bonus == Board.MULTIPLIER.TW){
            score *= 3;
        }

        score += crossBonus;

        if (count == 7){
            score = score + 50;
        }
        copyTrayLetters.clear();
        for (int i = 0; i < copyCopyTrayLetters.size(); i++){
            copyTrayLetters.add(copyCopyTrayLetters.get(i));
        }
        return score;
    }

    */
/**
     *
     * @param word
     * @param lastPos
     *//*

    public static void printHighestMove(String word, Pos lastPos){
        String tmpDirection = direction;
        direction = printTile.getDirection();
        Pos playPos = lastPos;
        int wordIdx = word.length() - 1;
        while (wordIdx >= 0){
            Tile playTile = new Tile();
            playTile.setData(word.charAt(wordIdx));
            if (trayLetters.contains(word.charAt(wordIdx)) &&
                    board.getTile(playPos).getData() != word.charAt(wordIdx)){
                trayLetters.remove(Character.valueOf(word.charAt(wordIdx)));
            }
            board.setTile(playTile, playPos.getX(), playPos.getY());
            wordIdx -= 1;
            playPos = before(playPos);
        }
        direction = tmpDirection;
    }

    */
/**
     * This function checks if the given position is an anchor position or not.
     * @param anchors
     * @param pos
     * @return boolean
     *//*

    public static boolean anchorContainsPos(List<Pos> anchors, Pos pos){
        for (int i = 0; i < anchors.size(); i++){
            if (anchors.get(i).getX() == pos.getX() && anchors.get(i).getY() == pos.getY()){
                return true;
            }
        }
        return false;
    }

    */
/**
     *This function is used to find all the character that can be played placed
     * at a given position, and builds a word. This function handles left part of
     * the word. If word doesn't use any letters on the board, then it builds the
     * whole word by itself.
     * @param root
     * @param partial_word
     * @param letters
     * @param anchorPos
     *//*

    public static void beforePart(TrieNode root,
                                  String partial_word, List letters, Pos anchorPos, int limit){
        extendAfter(root, partial_word, letters, anchorPos, false);
        if (limit > 0){
            for (char c : root.getChild().keySet()){
                if (letters.contains(c)){
                    letters.remove(Character.valueOf(c));
                    beforePart(root.getChild().get(c), partial_word + c,
                            letters, anchorPos, limit - 1);
                    letters.add(c);
                }
            }
        }
    }

    */
/**
     * This function checks if a character can be placed on the right or down side of the prefix.
     * if valid it calls function to print the word. If the word is not complete, it looks for letters
     * that are in the children nodes.
     * @param root
     * @param partial_word
     * @param letters
     * @param nextPos
     *//*

    public static void extendAfter(TrieNode root, String partial_word,
                                   List letters, Pos nextPos, boolean check){
        if (board.tileInBound(nextPos) && !board.isFilled(board, nextPos)
                && root.isCompleteWord() == true && check){
            legal_move(partial_word, before(nextPos));
        }

        if (board.tileInBound(nextPos)){
            if (board.tileInBound(nextPos.getX(), nextPos.getY())
                    && !board.isFilled(board, nextPos)) {
                for (char c : root.getChild().keySet()) {
                    List<Character> validLetters = new ArrayList<>();

                    for (Pos pos1 : crossCheckResult.keySet()){
                        if (pos1.getX() == nextPos.getX() && pos1.getY() == nextPos.getY()){
                            validLetters = crossCheckResult.get(pos1);
                        }
                    }


                    if (letters.contains(c) && validLetters.contains(c)){
                        letters.remove(Character.valueOf(c));
                        extendAfter(root.getChild().get(c), partial_word + c, letters,
                                after(nextPos), true);
                        letters.add(c);
                    }
                }
            }else{
                if (root.getChild().containsKey(board.getTile(nextPos).getData())){
                    extendAfter(root.getChild().get(board.getTile(nextPos).getData()),
                            partial_word + board.getTile(nextPos).getData(),
                            letters, after(nextPos), true);
                }
            }
        }
    }

    */
/**
     * This functions finds all the words that can be played given the
     * list of anchors.
     * @param letters
     *//*

    public static void findAllWords(List letters){
        String[] directions = new String[] {"across", "down"};
        printTile = new PrintTile();
        for (String checkDire : directions){
            direction = checkDire;
            List<Pos> anchors = findAnchor(board);
            crossCheckResult = crossChecks();
            for (Pos pos : anchors){
                if (board.tileInBound(before(pos)) && board.isFilled(board, before(pos))){
                    Pos newPos = before(pos);
                    String partial = String.valueOf(board.getTile(
                            newPos.getX(), newPos.getY()).getData());
                    while (board.tileInBound(before(newPos)) &&
                            board.isFilled(board, before(newPos))){
                        newPos = before(newPos);
                        partial = board.getTile(newPos).getData() + partial;
                    }
                    TrieNode node = root.search(partial);
                    if (node != null){
                        extendAfter(node, partial, letters, pos, false);
                    }
                }else{
                    int limit = 0;
                    Pos scanPos = pos;
                    boolean check = anchorContainsPos(anchors, before(pos));
                    while (board.tileInBound(before(scanPos)) &&
                            !board.isFilled(board, before(scanPos)) && !check){
                        limit = limit + 1;
                        scanPos = before(scanPos);
                    }
                    beforePart(root, "", letters, pos, limit);
                }
            }
        }
        printHighestMove(printTile.getWord(), printTile.getPos());
    }
}





*/
