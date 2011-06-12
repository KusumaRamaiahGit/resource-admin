<%@page import="model.Client"%>
<%@page import="java.util.*"%>
<%@page import="model.Reservation"%>
<%@page import="utils.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resource admin</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="block">
		<h1>Resource admin</h1>
		<%
			final int hourWidth = 20; // look at .timelineTable  td { width : %%px };
			Map<Client, List<Reservation>> reservationsMap = (Map<Client, List<Reservation>>) request
					.getAttribute("reservationsMap");
			//out.print(reservationsMap);
		%>
		<div id="reservations">
			<div class="head"><%=request.getAttribute("resourceName")%>
				| Зарезервированное время на
				<%
				out.print("" + request.getAttribute("day") + "."
						+ (Integer.parseInt(request.getAttribute("month").toString()) + 1) + "."
						+ request.getAttribute("year"));
			%>
			</div>
			<div>
				<table class="reservedTimeTable">
					<tr>
						<td>Логин</td>
						<td>
							<table class="timelineTable" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td>1</td>
									<td>2</td>
									<td>3</td>
									<td>4</td>
									<td>5</td>
									<td>6</td>
									<td>7</td>
									<td>8</td>
									<td>9</td>
									<td>10</td>
									<td>11</td>
									<td>12</td>
									<td>13</td>
									<td>14</td>
									<td>15</td>
									<td>16</td>
									<td>17</td>
									<td>18</td>
									<td>19</td>
									<td>20</td>
									<td>21</td>
									<td>22</td>
									<td>23</td>
									<td>24</td>
								</tr>

							</table></td>
					</tr>
					<%
						Iterator<Map.Entry<Client, List<Reservation>>> resMapIt = reservationsMap
								.entrySet().iterator();
						while (resMapIt.hasNext()) {
							Map.Entry<Client, List<Reservation>> entry = (Map.Entry<Client, List<Reservation>>) resMapIt
									.next();
					%>
					<tr class="time-trow">
						<td><%=entry.getKey()%></td>
						<td>
							<div class="bar-holder">
								<%
									Iterator<Reservation> dateListIt = entry.getValue().iterator();
										while (dateListIt.hasNext()) {
											Reservation curListRes = dateListIt.next();
											Calendar startTime = curListRes.getStart_time();
											Calendar endTime = curListRes.getEnd_time();
											Double barWidth = new Double(
													hourWidth
															* ((endTime.get(Calendar.HOUR_OF_DAY) + endTime
																	.get(Calendar.MINUTE) / 60.0) - (startTime
																	.get(Calendar.HOUR_OF_DAY) + startTime
																	.get(Calendar.MINUTE) / 60.0)));
											Double barLeft = new Double(
													hourWidth
															* (startTime.get(Calendar.HOUR_OF_DAY) + startTime
																	.get(Calendar.MINUTE) / 60.0));
								%>
								<div
									title="<%=curListRes%>"
									class="bar"
									style="left: <%=barLeft.intValue()%>px; width: <%=barWidth.intValue()%>px; background-position: -12px">
								</div>
								<%
									}
								%>
							</div>
						</td>
					</tr>

					<%
						}
					%>


				</table>
			</div>


			<center>
				<form method="POST" action="ReserveController">
					<input type="hidden" name="day"
						value="<%=request.getAttribute("day")%>" /> <input type="hidden"
						name="month" value="<%=request.getAttribute("month")%>" /> <input
						type="hidden" name="year"
						value="<%=request.getAttribute("year")%>" /> <input
						name="start_time" class='time-input' type="text" value="00:00" />
					<input name="end_time" class='time-input' type="text" value="23:45" />
					<input name="submit" type="submit" value="Выбрать" />
				</form>
				<p
					style="font-size: small; border-top: 1px #999 dashed; padding-top: 15px; color: #999;"><%=reservationsMap%></p>
			</center>
		</div>
	</div>

</body>
</html>