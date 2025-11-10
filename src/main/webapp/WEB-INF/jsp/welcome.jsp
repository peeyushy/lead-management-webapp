<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<script>
	function formatDate(isoString) {
	    // Example input: "2025-10-16T08:19:43.80629"
	    var date = new Date(isoString);
	    var dd = String(date.getDate()).padStart(2, '0');
	    var mm = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
	    var yyyy = date.getFullYear();
	    var hh = String(date.getHours()).padStart(2, '0');
	    var min = String(date.getMinutes()).padStart(2, '0');
	    return dd + '/' + mm + '/' + yyyy + ' ' + hh + ':' + min;
	}
	
	function format(rowData) {
	    var div = $('<div/>').addClass('loading').text('Loading...');
	    $.ajax({
	        url : '/leadnotes/' + rowData[1],
	        success : function(data) {
	            var childRows = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
	            $.each(data, function(index, value) {	            	
	                childRows += '<tr><td>'
	                    + $('<div>').text(formatDate(value.createdAt)).html()
	                    + '<div/></td><td>'
	                    + $('<div>').text(value.createdByUserId).html()
	                    + '<div/></td><td>' 
	                    + $('<div>').text(value.note).html()
	                    + '<div/></td></tr>';
	            });
	            childRows += '</table>';
	            div.html(childRows).removeClass('loading');
	        },
	        error : function(xhr, status, error) {
	            div.html('<p>Error loading notes.</p>').removeClass('loading');
	        }
	    });
	    return div;
	}

	$(document).ready(function() {
		var table = $('#leads').DataTable({
			"bLengthChange" : false,
			"bFilter" : false,
			"responsive" : false,
			"order" : [ [ 1, "desc" ] ],
			"columnDefs" : [ {
				"targets" : [ 0 ],
				"class" : 'details-control',
				"orderable" : false,
				"data" : null,
				"defaultContent" : ''
			}, {
				"targets" : [ 1 ],
				"visible" : false,
				"searchable" : false
			} ]
		});

		// Add event listener for opening and closing details
		$('#leads tbody').on('click', 'td.details-control', function() {
			var tr = $(this).parents('tr');
			var row = table.row(tr);

			if (row.child.isShown()) {
				// This row is already open - close it
				row.child.hide();
				tr.removeClass('shown');
			} else {
				// Open this row
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
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
				<h2 class="section-heading">Dashboard</h2>
				<hr class="section-heading-spacer">
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<table id="leads" class="display responsive nowrap"
					style="width: 100%">
					<caption>
						Table 1: <i>Leads.</i>
					</caption>
					<thead>
						<tr>
							<th></th>
							<th>Id</th>
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
								<td>${lead.id}</td>
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