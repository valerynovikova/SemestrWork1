package ru.itis.novikova.helper;
import ru.itis.novikova.dto.ArticleDTO;
import ru.itis.novikova.dto.ReportDTO;
import ru.itis.novikova.dto.UserDTO;

import java.util.List;

public class HTMLHelper {
	public static String makeReportHTML(List<ReportDTO> reports) {
		StringBuilder result = new StringBuilder();

		if(reports.size() == 0) {
			result.append("<p class=\"lead\">No reports that matches these requirements</p>");
		} else {
			for(ReportDTO report : reports) {
				result.append("<a href=\"/reportInfo?id=").append(report.getId()).append("\">")
						.append("<div class=\"alert alert-dark\" role=\"alert\">")
						.append("<h2>").append(report.getTitle()).append("</h2>")
						.append("<div>").append(report.getText()).append("</div>")
						.append("<br>")
						.append("<img src=\"").append(report.getPhotoUrl()).append("\" width=\"665\" height=\"350\">")
						.append("<br>").append("<br>")
						.append("<div><small class=\"text-muted\">").append(report.getUserNick())
						.append(" ").append(report.getData()).append("</small></div>")
						.append("<div><small class=\"text-muted\">Report ").append(report.getId()).append("</small></div>")
						.append("</div>").append("</a>");
			}
		}

		return result.toString();
	}

	public static String makeArticleHTML(List<ArticleDTO> articles) {
		StringBuilder result = new StringBuilder();

		if(articles.size() == 0) {
			result.append("<p class=\"lead\">No builds that matches requirements</p>");
		} else {
			for(ArticleDTO article : articles) {
				result.append("<a href=\"/articleInfo?id=").append(article.getId()).append("\">")
						.append("<div class=\"alert alert-dark\" role=\"alert\">")
						.append("<h2>").append(article.getTitle()).append("</h2>")
						.append("<div>").append(article.getText()).append("</div>")
						.append("<br>")
						.append("<img src=\"").append(article.getPhotoUrl()).append("\" width=\"665\" height=\"350\">")
						.append("<br>").append("<br>")
						.append("<div><small class=\"text-muted\">").append(article.getUserNick())
						.append(" ").append(article.getData()).append("</small></div>")
						.append("<div><small class=\"text-muted\">Article ").append(article.getId()).append("</small></div>")
						.append("</div>").append("</a>");
			}
		}

		return result.toString();
	}

	public static String makeUserHTML(List<UserDTO> users) {
		StringBuilder result = new StringBuilder();

		if(users.size() == 0) {
			result.append("<p class=\"lead\">No users with such nickname</p>");
		} else {
			for(UserDTO user : users) {
				result.append("<a href=\"/userInfo?id=").append(user.getId()).append("\">")
						.append("<div class=\"alert alert-dark\" role=\"alert\">")
						.append("<table>").append("<tr>")
						.append("<td><img alt=\"user_img\" src=\"").append(user.getAvatarUrl())
						.append("\" width=\"50\" height=\"50\" class=\"rounded-circle\"></td>")
						.append("<td>").append("<h3>")
						.append("<strong>").append(user.getNick()).append("</strong>")
						.append("</h3></td></tr></table></div></a>");
			}
		}

		return result.toString();
	}

}
