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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class game {

	public static void main(String[] args) throws IOException {
		int max;
		Scanner sc = new Scanner(System.in);
		//1-1：コンソール上に最初の文字を表示
		System.out.println("到達上限を入力して下さい");

		//1-2：入力した上限数値をStringで保管しコンソール上に表示
		String MAX = sc.next();
		for (int a = 0; a < 3; a++) {
			System.out.println("");
		} //3行空白
		System.out.println("目標到達数値は:【" + MAX + "】です");

		//1-3：上限数値をint型に置き換えた変数max
		max = Integer.parseInt(MAX);

		// 1-4：リスト型のnumbersに1～maxまでをStringに変換して入れる処理
		ArrayList<String> numbers = new ArrayList<String>();
		for (int n = 1; n <= max; n++) {
			numbers.add(String.valueOf(n));
		}

		//プレイヤー入力(仮決定のプレイヤーのリストを作成)
		ArrayList<String> players = new ArrayList<String>();
		try {
			players = players();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//先攻後攻決め(プレイヤー名も確定)
		ArrayList<String> player = playerRandom(players);

		//Startさせる
		start(numbers, player);
		sc.close();
	}

	@SuppressWarnings("rawtypes")
	public static void start(ArrayList number, ArrayList player) {
		//設定
		Scanner sc = new Scanner(System.in);
		ArrayList numbers = number;
		ArrayList players = player;
		StringBuilder alerts = new StringBuilder();
		int count = 0;
		int pl = 0;
		Boolean flag = true;
		String alerts01 = "1つずつ半角数値を入力し送信してください。1ターンで計3回入力できます\r\n";
		String alerts02 = "目標到達数値は【" + numbers.get(numbers.size() - 1) + "】です\r\n";
		String alerts03 = "1～2回入力後、相手へターンを渡す際には【A】を入力して下さい。\r\n";
		String alerts04 = "途中で終了する場合は【END】を入力して下さい。\r\n";

		while (flag == true) {
			//flagがtrueの時反復する
			for (int a = 0; a < 3; a++) {
				System.out.println("");
			} //3行空白
			System.out.println("「" + players.get(pl) + "」さん" + (count + 1) + "回目の数値を入力してください");
			String alerts05 = "次の数値は【" + numbers.get(0) + "】です。    残り入力回数は【" + (3 - count) + "】回です。\r\n";
			alerts = new StringBuilder();
			alerts.append(alerts01);
			alerts.append(alerts02);
			alerts.append(alerts03);
			alerts.append(alerts04);
			alerts.append(alerts05);
			System.out.println(alerts);

			String num = sc.next();

			//flagをfalseにする条件(ゲーム終了条件)
			//①	数値入力が上限数値の場合
			//②	ENDを入力した際
			if (!num.equals(numbers.get(0)) && !num.equals("A")) {
				System.out.println("入力した数値が不正です");
				continue;
			}

			//エラー系 PASS無効化
			if (num.equals("A") && count == 0) {
				System.out.println("1回目の入力なのでパスはできませんでした");
				continue;
			}
			if (num.equals(numbers.get(numbers.size() - 1)) || num.equals("END")) {
				flag = false;
				if (pl == 1) {
					count = 0; //初期に戻す
					pl = 0;
				} else if (pl == 0) {
					count = 0; //初期に戻す
					pl = 1;
				}
				break;
			}
			if(num.equals("A") && count !=0) {
				if (pl == 1) {
					count = 0; //初期に戻す
					pl = 0;
				} else if (pl == 0) {
					count = 0; //初期に戻す
					pl = 1;
				}
				continue;
			}
			//正規処理 入力数値とリストの0番目が等しいときで1回目か2回目の場合
			//countをインクリメントして、リストから要素を除く処理
			if (num.equals(numbers.get(0)) && count < 4) {
				count++; //この時点でcountが3になった場合は下記にて1に戻してプレイヤー交代
				numbers.remove(0);
				if (count == 3 && pl == 1) {
					count = 0; //初期に戻す
					pl = 0;
				} else if (count == 3 && pl == 0) {
					count = 0; //初期に戻す
					pl = 1;
				}
				continue;
			}

		}
		System.out.println("「" + players.get(pl) + "」さんの勝利です。");

		sc.close();
	}

	//プレイヤー変更ロジック
	public static int  playerchange(int player) {



		return player;
	}

	@SuppressWarnings("resource")
	public static ArrayList<String> players() throws IOException {

		ArrayList<String> players = new ArrayList<String>();

		Scanner sc = new Scanner(System.in);
		//プレイヤー名入力を表示
		System.out.println("プレイヤーAの名前を入力して下さい。");
		String namea = sc.next();
		System.out.print("プレイヤーAの名前は" + namea + "でよろしいですか？\r\n"
				+ "よろしければ【A】を入力しEnterを押下して下さい。\r\n"
				+ "変更の場合は新しいプレイヤーAの名前を入力して下さい。");

		String nameb = sc.next();
		System.out.println("nameb=" + nameb);
		if (!nameb.equals("A")) {
			do {
				namea = nameb;
				for (int a = 0; a < 3; a++) {
					System.out.println("");
				} //3行空白
				System.out.print("変更後のプレイヤーAの名前は" + namea + "でよろしいですか？\r\n"
						+ "よろしければ【A】を入力しEnterを押下して下さい。");
				nameb = sc.next();
			} while (!nameb.equals("A"));
		} else {
			for (int a = 0; a < 3; a++) {
				System.out.println("");
			} //3行空白
			System.out.print("【" + namea + "】さん 登録ありがとうございます。\r\n"
					+ "つづいてプレイヤーBの名前を入力して下さい。");
		}
		players.add(namea);
		namea = sc.next();
		for (int a = 0; a < 3; a++) {
			System.out.println("");
		} //3行空白
		System.out.print("プレイヤーBの名前は" + namea + "でよろしいですか？\r\r\n"
				+ "よろしければ【A】を入力しEnterを押下して下さい。\r\n"
				+ "変更の場合は新しいプレイヤーBの名前を入力して下さい。");

		nameb = sc.next();
		if (!nameb.equals("A")) {
			do {
				for (int a = 0; a < 3; a++) {
					System.out.println("");
				} //3行空白
				namea = nameb;
				System.out.print("変更後のプレイヤーAの名前は" + namea + "でよろしいですか？\r\n"
						+ "よろしければ【A】を入力しEnterを押下して下さい。");
				nameb = sc.next();
			} while (!nameb.equals("A"));
		} else {
			System.out.println("【" + namea + "】さん 登録ありがとうございます。");
		}
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
		for (int a = 0; a < 3; a++) {
			System.out.println("");
		} //3行空白
		System.out.print("先攻後攻をランダムで決定します\r\n"
				+ "先攻は「" + players.get(0) + "」さんに決まりました\r\n");

		return players;
	}
}