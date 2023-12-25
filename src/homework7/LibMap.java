package homework7;

import java.util.*;

public class LibMap {
    public static void main(String[] args) {

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Жаран цагаан хонь", 1000);
        hashMap.put("Ногоон нүдэн лам", 1500);
        hashMap.put("Зуун жилийн ганцаардал", 387);
        hashMap.put("Анна Каренина", 450);
        hashMap.put("Эрэг дээрх Кафка", 532);
        hashMap.put("Норвегийн ой", 532);
        hashMap.put("4 биш 4", 312);
        hashMap.put("Агуу Гетсби", 1123);
        hashMap.put("Танихгүй эмэгтэйн захидал", 453);
        hashMap.put("Зоригт Анчин Бүсгүй", 500);
        hashMap.put("Миний түүх", 489);
        hashMap.put("Шик хатагтайн зөвлөгөө", 342);
        hashMap.put("Цас", 674);
        hashMap.put("Монте Кристо Гүн", 1245);
        hashMap.put("Алхимич", 312);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Хайх номыг нэрээр хайх бол 1, хуудсаар хайх бол 2 ын тоог оруулна уу");
            int myInt = Integer.parseInt(scanner.nextLine());

            if(myInt==1){
                System.out.println("Хайх номыг нэрийг оруулна уу: ");
                String str = scanner.nextLine();
                if(hashMap.containsKey(str)) {
                    System.out.println(str +" ном номын санд байна.");
                }
                else System.out.println(str +" ном номын санд байхгүй.");

            } else if (myInt==2) {
                System.out.println("Хайх номын хуудсын тоог оруулна уу: ");
                int pageNumber = Integer.parseInt(scanner.nextLine());
                for (Map.Entry<String, Integer> set :
                        hashMap.entrySet()) {
                    if(set.getValue()==pageNumber){
                        System.out.println(set.getKey() + " ном " +set.getValue() + " хуудастайгаар номын санд байна.");
                    }
                }
            }
            else System.out.println("1 эсвэл 2 ын тоо оруулна уу");
        }
    }
}