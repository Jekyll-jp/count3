/**
 * 数値を1回～3回入力していき、最後に最終ナンバーを入力させた方が勝者となる
 *
 * class Main >>> メインロジック
 * ArrayList<String> players() >>> プレイヤーの名前を決めるロジック
 * ArrayList<String> playerRandom(ArrayList<String> list)  >>> 先攻後攻を決めるロジック
 *
 *
 *
 */

package count3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		int max;
		Scanner sc = new Scanner(System.in);
		//1-1：コンソール上に最初の文字を表示
		System.out.println("到達上限を入力して下さい");

		//1-2：入力した上限数値をStringで保管しコンソール上に表示
		final String MAX = sc.next();
		System.out.println("目標到達数値は:【" + MAX + "】です");

		//1-3：上限数値をint型に置き換えた変数max
		max = Integer.parseInt(MAX);

		// 1-4：リスト型のnumbersに1～maxまでをStringに変換して入れる処理
		ArrayList<String> numbers = new ArrayList<String>();
		for (int n = 1; n <= max; n++) {
			numbers.add(String.valueOf(max));
		}

		//プレイヤー入力(仮決定のプレイヤーのリストを作成)
		ArrayList<String> players = new ArrayList<String>();
		players = players();

		//先攻後攻決め(プレイヤー名も確定)
		ArrayList<String> player = playerRandom(players);

	}

	public static void start() {
		//1ターン目開始
		StringBuilder alerts = new StringBuilder();
		int count = 1;
		int pl = 0;
		Boolean flag = true;
		String alerts01 = "1つずつ半角数値を入力し送信してください。1ターンで計3回入力できます";
		String alerts02 = "次の数値は【" + numbers.get(0) + "】です。    残り入力回数は【" + (4 - count) + "】回です。";
		alerts.append(alerts01);
		System.out.println(alerts);
			System.out.println("「"+player.get(pl)+"」さん"+count+"回目の数値を入力してください");
			System.out.println(alerts01);
			String number =sc.next();
			if(number.equals("A") && count==0) {
				System.out.println("1回目の入力なのでパスはできませんでした");

			}else if() {

			}
			while(sc.hasNext()) {

				if(count == 3 ll number.equals("A")) {

				}

				if(number == numbers.get(0)) {

					count++;
				}else {

				}
			}

		}

	public static ArrayList<String> players() {

		ArrayList<String> players = new ArrayList<String>();

		Scanner sc = new Scanner(System.in);

		//プレイヤー名入力を表示
		System.out.println("プレイヤーAの名前を入力して下さい。");
		String namea = sc.next();
		System.out.print("プレイヤーAの名前は" + namea + "でよろしいですか？¥n"
				+ "よろしければ【A】を入力しEnterを押下して下さい。¥n"
				+ "変更の場合は新しいプレイヤーAの名前を入力して下さい。");

		String nameb = sc.next();

		do {
			namea = nameb;
			System.out.print("変更後のプレイヤーAの名前は" + namea + "でよろしいですか？¥n"
					+ "よろしければ【A】を入力しEnterを押下して下さい。");
		} while (!nameb.equals("A"));

		System.out.print("【" + namea + "】さん 登録ありがとうございます。¥n"
				+ "つづいてプレイヤーBの名前を入力して下さい。");
		players.add(namea);
		namea = sc.next();
		System.out.print("プレイヤーBの名前は" + namea + "でよろしいですか？¥n"
				+ "よろしければ【A】を入力しEnterを押下して下さい。¥n"
				+ "変更の場合は新しいプレイヤーBの名前を入力して下さい。");

		nameb = sc.next();
		do {
			namea = nameb;
			System.out.print("変更後のプレイヤーAの名前は" + namea + "でよろしいですか？¥n"
					+ "よろしければ【A】を入力しEnterを押下して下さい。");
		} while (!nameb.equals("A"));

		System.out.println("【" + namea + "】さん 登録ありがとうございます。");
		players.add(namea);
		return players;
	}

	public static ArrayList<String> playerRandom(ArrayList<String> list) {
		ArrayList<String> players = list;
		Random rnd = new Random();
		int second = rnd.nextInt(2);
		if (second == 1) {
			players.add(players.get(0));
			players.remove(0);
		}
		System.out.print("先攻後攻をランダムで決定します¥n"
				+ "先攻は「" + players.get(0) + "」さんに決まりました");

		return players;

	}

	sc.close();
}