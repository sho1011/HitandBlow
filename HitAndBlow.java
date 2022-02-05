import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HitAndBlow {
    public static void main(String[] args){
        //答えの生成
        String[] answer = new String[5];

        for( int i = 0; i < 5; i++){
            while(true){
                //乱数をnumStringに格納する
                double randomValue = Math.random() * 10;
                int num = (int) randomValue;
                String numString = String.valueOf(num);

                
                //生成した数値が配列にあるかをチェック
                boolean isExistNum = false;
                for(int j = 0; j < 5; j++){
                    if(answer[j] != null && answer[j].equals(numString)){
                        isExistNum = true;
                    }
                }

                if(!isExistNum){
                    answer[i] = numString;
                    break;
                }
            }
        }
        //デバック用に答えを出力しておく
        System.out.println("answer :" + Arrays.toString(answer));
        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Hit and Blow を開始します");
            int count = 0;

            while(true){
                count++;
                System.out.println("0 - 9 の数値を半角スペース区切りで重複の内容に5つ入力してください");

                //ユーザーの入力を受け付ける
                String inputValue = br.readLine();

                //ユーザー入力をチェックする
                if(inputValue == null || inputValue.isEmpty()){
                    //未入力なら処理を行わない
                    continue;
                }
                //入力された数値がスペース区切りで5つじゃなかったら処理は行わない
                String[] reply = inputValue.split(" ");

                if(reply.length != 5){
                    continue;
                }

                //重複は以降の処理は行わない
                boolean isDuplicate = false;

                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        if(i != j){
                            if(reply[i].equals(reply[j])){
                                isDuplicate = true;
                            }
                        }
                    }
                }
                if(isDuplicate){
                    continue;
                }
                
                boolean isNotNumber = false;
                
                for(int i = 0; i < 5; i++){
                    try{
                        Integer.parseInt(reply[i]);

                    }catch(NumberFormatException e){
                        isNotNumber = true;
                    }
                }
                if(isNotNumber){
                    continue;
                }

                //Hit Blow の判定
                int hitCounter = 0;
                int blowCounter = 0;
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        if(i == j && answer[i].equals(reply[j])){
                            hitCounter++;
                        }
                        if(i != j && answer[i].equals(reply[j])){
                            blowCounter++;
                        }
                    }
                }
                

                //結果表示
                if(hitCounter == 5){
                    System.out.println("正解です");
                    System.out.println("正解までに" + count + "回かかりました");
                    System.out.println("おめでとうございます！");
                    break;
                }else{
                    System.out.println(hitCounter + "H " + blowCounter + "B");
                }
            }
        }
        catch(Exception e){
            System.out.println("予期しないエラーが発生しました");
        }
        System.out.println("Hit and Blowを終了します");
    }
}
