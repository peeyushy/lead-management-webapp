<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<script>
	$(document).ready(function() {		
		$('#table_user').DataTable({
			"bLengthChange" : false
		});
	});
</script>
<style>
.dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter,
	.dataTables_wrapper .dataTables_info {
	float: none;
	text-align: left;
}
</style>
<!-- Page Content -->
<div class="content-section-b">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12" id="panel">
				<h2 class="section-heading">
					Users
					<security:authorize access="hasAnyAuthority('ADMIN')">
						<a href="adduser"
							class="btn btn-default btn-sm btn-style pull-right">+ Add</a>
					</security:authorize>
				</h2>
				<hr class="section-heading-spacer">
				<div class="clearfix"></div>
				<c:if test="${not empty msg}">
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-info alert-dismissible">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> ${msg}
							</div>
						</div>
					</div>
				</c:if>
				<table id="table_user" class="display responsive nowrap"
					style="width: 100%">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Role</th>
							<th>Team Leader</th>
							<th>User Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.id}</td>
								<td>${user.fullname}</td>
								<td>${user.email}</td>								
								<td>${user.role}</td>
								<td>${user.teamleaderid}</td>
								<td>${user.username}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</div>
<%@ include file="common/footer.jspf"%>