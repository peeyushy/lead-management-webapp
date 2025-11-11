<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<script>
	$(document).ready(function() {		
		$('#table_leads').DataTable({
			"bLengthChange" : false,
			"order": [[ 0, "desc" ]],
			"columnDefs" : [ {
				"targets" : [ 0 ],
				"visible" : false,
				"searchable" : false
			} ]
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
					Leads <a href="addlead"
						class="btn btn-default btn-sm btn-style pull-right">+ Add</a>
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
				<table id="table_leads" class="display responsive nowrap"
					style="width: 100%">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th>Name</th>
							<th>Email</th>
							<th>Contact#</th>
							<th>Status</th>
							<security:authorize access="hasAnyAuthority('ADMIN') or hasAnyAuthority('TEAM_LEADER')">
								<th>Assignee</th>
								<!-- <th>Delete</th> -->
							</security:authorize>
							<!-- <th>Notes</th> -->							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${leads}" var="lead">
							<tr>
							    <td></td>
							    <td>
							        <a href="editlead?id=${lead.id}">
							            <i class="fa fa-pencil" aria-hidden="true"></i>
							        </a>
							    </td>
							    <td>${lead.firstName} ${lead.lastName}</td>
							    <td>${lead.email}</td>
							    <td>${lead.contactno}</td>
							    <td>${lead.status}</td>
							    <security:authorize access="hasAnyAuthority('ADMIN') or hasAnyAuthority('TEAM_LEADER')">
							        <td>${lead.assignedUserName}</td>
							    </security:authorize>
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