import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        // Task 1
        List<Integer> nums = new ArrayList<>(List.of(1, 1, 2, 3, 4, 4, 5, 5, 6, 7));
        System.out.print("nums odd: [ ");
        for (int i : nums) {
            if(i%2 != 0)
                System.out.print(i + " ");
        }
        System.out.println("]");

        // Task 2
        int n;
        Collections.sort(nums);
        List<Integer> newNums = new ArrayList<>();
        for (int i : nums) {
            n = newNums.size();
            if(i % 2 == 0 && n == 0)
                newNums.add(i);
            else if(i % 2 == 0 && newNums.get(n-1) != i)
                newNums.add(i);
        }
        System.out.print("nums even in sorted order: [ ");
        for (int i : newNums) {
            System.out.print(i + " ");
        }
        System.out.println("]");

        // Task 2. Version with SortedSet
        SortedSet<Integer> sortedNums = new TreeSet<>();
        for (int i : nums) {
            if(i % 2 == 0)
                sortedNums.add(i);
        }
        System.out.println("sortedNums = " + sortedNums);

        // Task 3
        final String text = "МИР ТАКОВ, КАКОВ ОН ЕСТЬ (По А.Моруа)\n Мир таков, каков он есть. Трудно предположить, "
                + "чтобы мир был создан единственно для удовлетворения наших потребностей. Это было бы чудом из чудес. "
                + "Мир нейтрален. Он не дружествен и не враждебен человеку. Вам внушили, что человек рождается для того, "
                + "чтобы умереть, и что вы должны всю жизнь терзаться этой мыслью. Чего ради? Смерть - не факт сознания. "
                + "\"Смысл раздумий о смерти в том, что они лишены смысла\", - писал Монтерлан. Смерть близких "
                + "потрясает нас. А наша собственная? Бояться ее - значит представлять себе и мир, где мы есть, и мир, "
                + "где нас нет. Эти два образа несовместимы.\nВам внушили, что мы живем на краю пропасти…, но даже "
                + "если мы идем по краю пропасти, ничто не толкает нас вниз.\nВам внушили, что старые моральные "
                + "ценности канули в прошлое*. Это ложь… Я напомню вам для начала несколько древних как мир истин…\n"
                + "…Нельзя жить для себя. Думая только о себе, человек всегда найдет тысячу причин чувствовать себя "
                + "несчастным. Никогда он не делал всего того, что хотел и должен был делать, никогда не получал всего "
                + "того, чего, по его мнению, заслуживал, редко был любим так, как мечтал быть любимым. Без конца "
                + "пережёвывая свое прошлое, он будет испытывать одни сожаления… Зачеркнуть прошлое все равно "
                + "невозможно, попытайтесь лучше создать настоящее, которым вы… сможете гордиться. Всякий, кто живет "
                + "ради других - ради своей страны, ради женщины, ради творчества, ради голодающих или гонимых, - "
                + "словно по волшебству забывает свою тоску…\nВторое правило - надо действовать. Вместо того, чтобы "
                + "жаловаться на абсурдность мира, постараемся преобразить тот уголок, куда забросила нас судьба. Мы не "
                + "в силах изменить вселенную, да и не стремимся к этому. Наши цели ближе и проще: заниматься своим "
                + "делом - правильно выбрать его, глубоко изучить и достичь в нем мастерства… Если человек в "
                + "совершенстве овладел каким-нибудь ремеслом, работа приносит ему счастье.\nТретье правило - надо "
                + "верить в силу воли… Безусловно, никто из нас не всемогущ. Не в моей власти помешать войне, но мои "
                + "призывы, помноженные на призывы миллионов других людей, ослабят угрозу войны. Я не в силах выиграть "
                + "битву, но я в силах быть храбрым солдатом, исполнить свой долг. \"Возможности наши зависят от того, "
                + "на что мы дерзнём,\" поэтому надо быть всегда в форме. Усилием воли человек заставляет себя трудиться "
                + "на совесть и совершать геройские поступки. Быть может, воля и есть царица добродетелей.\n Важно и "
                + "четвертое правило - надо хранить верность. Верность слову, обязательствам, другим, самому себе.";

        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        SortedSet<String> words = new TreeSet<>();
        while(matcher.find()){
            words.add(matcher.group().toLowerCase());
        }

        // Difficult printing of words cloud
        StringBuilder str = new StringBuilder("unique word set = { ");
        ArrayList wordsList = new ArrayList(words);
        int i = 0;
        while(i < wordsList.size()) {
            while (str.length() < 160 && i < wordsList.size()) {
                str.append(wordsList.get(i++));
                str.append(" ");
            }
            System.out.println(str);
            str.delete(0, str.length());
            str.append("\t");
        }
        System.out.println("}");

        // Task 4
        Map<String, Integer> wordsMap = new HashMap<>();
        pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(text);
        while(matcher.find()){
            String s = matcher.group().toLowerCase();
            if(wordsMap.containsKey(s)) {
                wordsMap.put(s, wordsMap.get(s)+1);
            } else {
                wordsMap.put(s, 1);
            }
        }

        // Printing all repeated words with counts
        for (Map.Entry<String, Integer> word : wordsMap.entrySet()) {
            if(word.getValue() > 1)
                System.out.println("repeated word: " + word);
        }
        System.out.println();

        // Maximum repeated word (only first one)
        String maxKey = null;
        int maxValue = 0;
        for (Map.Entry<String, Integer> word : wordsMap.entrySet()) {
            if(word.getValue() > maxValue) {
                maxKey = word.getKey();
                maxValue = word.getValue();
            }
        }
        if(maxKey != null)
            System.out.println("Maximum repeated word: " + maxKey + " = " + maxValue);

    }

}