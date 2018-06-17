package by.htp.news.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import by.htp.news.bean.Article;
import by.htp.news.service.ArticleService;

public class ArticleController {

	private static ArticleService articleService;
	private static ClassPathXmlApplicationContext context;
	private static int value;
	private static int id;
	private static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws InterruptedException {

		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		while (value != 999) {
			try {
				System.out.println("Новости на сегодня!");
				System.out.println("Введите ID новости, которую хотите прочесть");
				System.out.println("Введите 333, чтобы добавить новость");
				System.out.println("Введите 999, чтобы выйти");
				printBorder();
				printLoading();
				printBorder();
				Map<Integer, Article> articles = getList();
				if(!articles.isEmpty()) {
					
				printList(articles);
				printBorder();
				}else {
					printBorder();
					System.out.println("Список новостей пуст");
					printBorder();
				}
				
				
				String temp = buffer.readLine();
				if(check(temp)) {
				id = Integer.parseInt(temp);
				}
				
				if (id == 333) {
					addArticle();
					TimeUnit.SECONDS.sleep(1);
				} else if(id == 999) {
					break;
				} else {
					readArticleByID(id, articles);
					printBorder();
					System.out.println("Нажмите 10, чтобы удалить новость Или любую клавишу, чтобы продолжить");
					value = Integer.parseInt(buffer.readLine());
					if (value == 10) {
						deleteArticle(id);
					}
				}

				printBorder();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		context.close();
	}

	public static void readArticleByID(int id, Map<Integer, Article> articles) {
		articleService = (ArticleService) context.getBean("myArticleService", ArticleService.class);
		articleService.readArticleByID(id, articles);

	}

	public static void printList(Map<Integer, Article> articles) {
		for (Article value : articles.values()) {
			System.out.println(value.toString());
		}

	}

	public static Map<Integer, Article> getList() {
		articleService = (ArticleService) context.getBean("myArticleService", ArticleService.class);
		Map<Integer, Article> articles = articleService.getList();
		return articles;
	}

	public static void deleteArticle(int id) {
		articleService = (ArticleService) context.getBean("myArticleService", ArticleService.class);
		// not working
		// System.out.println(article.toStringFull());
	}

	public static void printText(List<String> array) {
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}

	}

	public static String dater() {
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy.MM.dd");
		String nowDate = simple.format(date).replace(".", "-");
		return nowDate;
	}

	public static void addArticle() {

		try {
			System.out.println("Введите название статьи");
			String title = buffer.readLine();
			String date = dater();
			System.out.println("Введите раздел");
			String heading = buffer.readLine();
			System.out.println("Введите краткое описание");
			String annotatio = buffer.readLine();
			System.out.println("Введите текст");
			String text = buffer.readLine();
			Article article = new Article(title, date, heading, annotatio, text);
			articleService = (ArticleService) context.getBean("myArticleService", ArticleService.class);
			articleService.addArticle(article);
		} catch (IOException e) {
			System.err.println("");
			e.printStackTrace();
		}

	}

	public static void printBorder() {
		System.out.println(
				"=========================================================================================================");
	}
	
	
	public static boolean check(String str) {
		try {
			Integer.parseInt(str);
			
		}catch(NumberFormatException e) {
			return false;
		}
			
		return true;
	}
	
	
	
	public static void printLoading() {
		String [] load = new String[7];
		load[0] = "//        //////        ////  ////////  ////////  ///     //   ///////";
		load[1] = "//       ///  ///      // //  //    ///    //     ////    //  //";
		load[2] = "//       //    //     //  //  //     //    //     // //   //  //";
		load[3] = "//       //    //    //   //  //     //    //     //  //  //  //   ///";
		load[4] = "//       //    //   ////////  //     //    //     //   // //  //    //";
		load[5] = "//       ///  ///  //     //  //    ///    //     //    ////  //    //";
		load[6] = "////////  //////  //      //  ////////  ////////  //     ///   ///////";
		
		for(int i = 0; i < load.length; i++) {
			try {
				System.out.println(load[i]);
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
