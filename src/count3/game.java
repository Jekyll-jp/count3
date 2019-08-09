/**
 * 数値を1回～3回入力していき、最後に最終ナンバーを入力させた方が勝者となる
 *
 **void main(String[] args) throws IOException >>> メインロジックというか設定
 **ArrayList<String> players() >>> プレイヤーの名前を決めるロジック
 **ArrayList<String> playerRandom(ArrayList<String> list)  >>> 先攻後攻を決めるロジック
 **int  playerchange(int player) >>> 対戦時プレイヤーを変更するロジック
 **void start(ArrayList number, ArrayList player) >>> 対戦ロジック
 **void space(int space) >>> コンソールを少しでも見やすくする為にspaceを入力するロジック
 */

package count3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class game {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		//コンソール上に最初の文字を表示
		System.out.println("到達上限数値を半角で入力して下さい");

		//入力した上限数値をStringで保管しコンソール上に表示
		String MAX = sc.next();
		space(3);
		System.out.println("目標到達数値は:【" + MAX + "】です");

		//リスト型のnumbersに1～MAXまでをStringに変換して入れる処理
		ArrayList<String> numbers = new ArrayList<String>();
		for (int n = 1; n <= Integer.parseInt(MAX); n++) {
			numbers.add(String.valueOf(n));
		}

		//プレイヤー入力(仮決定のプレイヤーのリストを作成)
		ArrayList<String> players = players();

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
			space(3);
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

			//入力したものが半角数値でない場合("A"は除く)
			if (!num.equals(numbers.get(0)) && !num.equals("A")) {
				System.out.println("入力した数値が不正です");
				continue;
			}

			//終了条件(最後まで)
			if (num.equals(numbers.get(numbers.size() - 1)) || num.equals("END")) {
				flag = false;
				count = 0;
				pl = playerchange(pl);
				break;
			}

			//相手へパスするとき
			if (num.equals("A")) {
				if (count == 0) { //パスできないのにパスした場合
					System.out.println("1回目の入力なのでパスはできませんでした");
				} else { //パスできる場合
					count = 0;
					pl = playerchange(pl);
				}
				continue;
			}

			//正規処理 入力数値とリストの0番目が等しいときで1回目か2回目の場合
			//countをインクリメントして、リストから要素を除く処理
			if (num.equals(numbers.get(0)) && count < 4) {
				count++;
				numbers.remove(0);
				//この時点でcountが3の場合countを1に戻してプレイヤー交代
				if (count == 3) {
					count = 0;
					pl = playerchange(pl);
				}
				continue;
			}

		}
		System.out.println("「" + players.get(pl) + "」さんの勝利です。");
		sc.close();
	}

	public static int playerchange(int player) {

		if (player == 1) {
			player = 0;
		} else if (player == 0) {
			player = 1;
		} else {
			System.out.println("プレイヤー変更の際にエラーが発生しました。プログラムを終了します。");
			System.exit(0);
		}
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
				space(3);
				System.out.print("変更後のプレイヤーAの名前は" + namea + "でよろしいですか？\r\n"
						+ "よろしければ【A】を入力しEnterを押下して下さい。");
				nameb = sc.next();
			} while (!nameb.equals("A"));
		} else {
			space(3);
			System.out.print("【" + namea + "】さん 登録ありがとうございます。\r\n"
					+ "つづいてプレイヤーBの名前を入力して下さい。");
		}
		players.add(namea);
		namea = sc.next();
		space(3);
		System.out.print("プレイヤーBの名前は" + namea + "でよろしいですか？\r\r\n"
				+ "よろしければ【A】を入力しEnterを押下して下さい。\r\n"
				+ "変更の場合は新しいプレイヤーBの名前を入力して下さい。");

		nameb = sc.next();
		if (!nameb.equals("A")) {
			do {
				space(3);
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
		space(3);
		System.out.print("先攻後攻をランダムで決定します\r\n"
				+ "先攻は「" + players.get(0) + "」さんに決まりました\r\n");

		return players;
	}

	public static void space(int space) {
		for (int a = 0; a < space+1; a++) {
			System.out.println("");
		}
	}

}