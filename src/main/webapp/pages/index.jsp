<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- to create spring mvc form (ctrl+double space) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
	crossorigin="anonymous">

</head>
<body>

	<div class="container">
		<h3 class="pb-3 pt-3">Report Application</h3>
		<!--pb=padding bottom, pt=padding top  -->

		<!--BELOW DISPLAY THE FORM  -->

		<form:form action="search" modelAttribute="search" method="POST">
			<table>

				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<!--TO DISPLAY DROPDOWN  -->
							<form:option value="">-select-</form:option>
							<!--displaying option in form  -->

							<form:options items="${names}" />



						</form:select></td>

					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${statuses }" />
						</form:select></td>

					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select></td>
				</tr>


				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date"
							data-date-format="mm/dd/yyyy" /></td>

					<td>End Date:</td>
					<td><form:input type="date" path="endDate" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Search"
						class="btn btn-primary" /></td>
				</tr>


			</table>

		</form:form>
		<hr />

		<!--iterating data in UI  -->
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>S.No</th>
					<th>Holder Name</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amt</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenName}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.benefitAmt}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<hr />

		Export : <a href="">Excel</a> <a href="">Pdf</a>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
		crossorigin="anonymous"></script>

</body>
</html>