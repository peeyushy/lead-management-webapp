<%@page import="com.fhc.constants.AppConstants"%>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#table_quotes').DataTable({
			"bLengthChange" : false,
			"bFilter" : false,
			"order" : [ [ 1, "asc" ] ]
		});
		//need to get old dates disable, check why minutes is not set as 00 etc.
		$("#datetimepicker1").datetimepicker({
			minView : 1,
			format : "dd/mm/yyyy hh:ii",
			autoclose : true,
			todayBtn : true,
			todayHighlight : 1,
			fontAwesome : true
		});
	});
</script>
<!-- Page Content -->
<div class="content-section-b">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="section-heading">
					<c:out value="${action}" />
					Lead
				</h2>
				<hr class="section-heading-spacer">
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<form:form method="post" modelAttribute="lead">
					<c:set var="leadErrors">
						<form:errors path="*" />
					</c:set>
					<c:if test="${not empty leadErrors}">
						<div class="row">
							<div class="col-lg-12">
								<div class="alert alert-danger alert-dismissible">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> ${leadErrors}
								</div>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="firstName">First Name</label>
								<form:input type="text" class="form-control autocomplete"
									id="firstName" path="firstName"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="First Name" required="required" />
								<p class="note">First name.</p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="lastName">Last Name</label>
								<form:input type="text" class="form-control autocomplete"
									id="lastName" path="lastName"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="Last Name" required="required" />
								<p class="note">Last Name.</p>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="email">Email</label>
								<form:input type="text" class="form-control autocomplete"
									id="email" path="email"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="someone@example.com" required="required" />
								<p class="note">Please enter a valid email id.</p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="contactno">Contact#</label>
								<form:input type="text" class="form-control autocomplete"
									id="contactno" path="contactno"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="Contact no" required="required" />
								<p class="note">Please enter a valid contact number.</p>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="project">Project</label>
								<form:input type="text" class="form-control autocomplete"
									id="project" path="project"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="Ace" />
								<p class="note">Please enter the project name.</p>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="area">Requirement</label>
								<form:input type="text" class="form-control autocomplete"
									id="requirement" path="requirement"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="4Bhk" />
								<p class="note">Please enter the requirement.</p>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="budget">Budget</label>
								<form:input type="text" class="form-control autocomplete"
									id="budget" path="budget"
									onkeyup="return autoCompleteListener(this, event);"
									placeholder="> 1Cr" />
								<p class="note">Please enter aprox budget.</p>
							</div>
						</div>
						
						<div class="col-lg-6">
							<div class="form-group">
								<label for="source">Source</label>
								<form:select class="form-control" path="source"
									required="required">
									<form:options items="${sourceMap}" />
								</form:select>
								<p class="note">Select lead source.</p>
							</div>
						</div>						
					</div>

					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="ltype">Assignee</label>
								<form:select path="assignedUserId" items="${usersMap}"
									class="form-control" required="required">
									<c:forEach var="user" items="${usersMap}">
										<option value="${user.key}"
											${user.key == assignedUserId ? 'selected="selected"' : ''}>${user.value}</option>
									</c:forEach>
								</form:select>
								<p class="note">Assignee to this lead.</p>
							</div>
						</div>						
						<div class="col-lg-6">
							<div class="form-group">
								<label for="status">Status</label>
								<form:select class="form-control" path="status"
									required="required">
									<form:options items="${statusMap}" />
								</form:select>
								<p class="note">Select lead status.</p>
							</div>
						</div>
					</div>					
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
							    <label for="newNote">Add New Note:</label>
							    <textarea id="newNote" name="newNote" class="form-control" rows="4" required="required" placeholder="Type your new note here..."></textarea>
								<p class="note">Please add any additional comments that
																	defines your actions, this will help in understanding your lead
																	better.</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
						    <label>Existing Notes:</label>
						    <ul>
						        <c:forEach var="note" items="${lead.notes}">
						            <li>${note.createdAt} : ${note.note}</li> <!-- Change 'content' to the actual field name in your Note entity -->
						        </c:forEach>
						    </ul>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12">
							<div class="group">
								<c:choose>
									<c:when test="${not empty cid}">
										<a href="edit-client?cid=${cid}"
											class="btn btn-default btn-lg btn-style">&larr; Back</a>&nbsp;
  									</c:when>
									<c:otherwise>
										<a href="leads" class="btn btn-default btn-lg btn-style">&larr;
											Back</a>&nbsp;
									</c:otherwise>
								</c:choose>
								<form:button type="submit"
									class="btn btn-default btn-lg btn-style">Save</form:button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</div>

<%@ include file="common/footer.jspf"%>