<%@page import="com.fhc.constants.ReqStatus"%>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/publicnavigation.jspf"%>

<!-- Page Content -->
<div class="content-section-b">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="section-heading">Send Quote</h2>
				<hr class="section-heading-spacer">
				<div class="clearfix"></div>
			</div>
		</div>
		<form:form method="post" modelAttribute="requirement"
			accept-charset="UTF-8">
			<div class="row">
				<div class="col-lg-6">
					<c:set var="sendQuotesErrors">
						<form:errors path="*" />
					</c:set>
					<c:if test="${not empty sendQuotesErrors}">
						<div class="row">
							<div class="col-lg-12">
								<div class="alert alert-danger alert-dismissible">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> ${sendQuotesErrors}
								</div>
							</div>
						</div>
					</c:if>
					<table class="table table-hover" id="tablediv">
						<tbody>
							<tr>
								<td>Need</td>
								<td>${requirement.reqtype}</td>
							</tr>
							<tr>
								<td>When</td>
								<td><fmt:parseDate value="${requirement.reqdatetime}"
										pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
									<fmt:formatDate pattern="dd/MM/yyyy HH:mm"
										value="${parsedDateTime}" /></td>
							</tr>
							<tr>
								<td>Source</td>
								<td>${requirement.reqpickuploc}</td>
							</tr>
							<tr>
								<td>Destination</td>
								<td>${requirement.reqdroploc}</td>
							</tr>
							<tr>
								<td>Load Type</td>
								<td>${requirement.ltype}</td>
							</tr>
							<tr>
								<td>Vehicle Type</td>
								<td>${requirement.vtype}</td>
							</tr>
							<tr>
								<td>Owner's Comment</td>
								<td>${requirement.reqComment}</td>
							</tr>
							<tr>
								<td>Additional Details</td>
								<td><div id="panel" /></td>
							</tr>
							<tr>
								<td>Quote (Rs.)</td>
								<td><form:input class="form-control" id="quote"
										type="number" path="quote" required="required"
										placeholder="Rs." autofocus="autofocus" />
									<p class="note">Please add/update your quote for this
										requirement.</p></td>
							</tr>
							<tr>
								<td>Comment</td>
								<td><form:textarea class="form-control" rows="2"
										id="comments" path="clientComment"
										placeholder="Leave a comment" />
									<p class="note">Please add additional comments, this will
										help us understand your quote better.</p></td>
							</tr>
							<tr>
								<td colspan="2"><div class="group">
										<c:choose>
											<c:when test="${not empty cid}">
												<c:url value="${pageContext.request.contextPath}/noauth"
													var="url">
													<c:param name="cid" value="${cid}" />
												</c:url>
												<a href="${url}" class="btn btn-default btn-lg btn-style">&larr;
													Back</a>&nbsp;
  									</c:when>
										</c:choose>
										<form:button type="submit"
											class="btn btn-default btn-lg btn-style">Send</form:button>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-lg-6">
					<div id="map" style="height: 600px;"></div>
				</div>
			</div>
		</form:form>
	</div>
	<!-- /.container-fluid -->
</div>
<script type="text/javascript" charset="UTF-8">
/**
 * Calculates and displays a route from source & destination.
 * A full list of available request parameters can be found in the Routing API documentation.
 * see:  http://developer.here.com/rest-apis/documentation/routing/topics/resource-calculate-route.html
 * @param   {H.service.Platform} platform    A stub class to access HERE services
 */
function calculateRouteFromAtoB (platform) {
	var router = platform.getRoutingService(),
	  routeRequestParams = {
	    mode: 'fastest;truck',
	    representation: 'display',
	    routeattributes : 'waypoints,summary,shape,legs',
	    maneuverattributes: 'direction,action',      
	    waypoint0: '${requirement.pickuplat},${requirement.pickuplong}', // Source
	    waypoint1: '${requirement.droplat},${requirement.droplong}'  // Destination
	  };
	
	router.calculateRoute(
	  routeRequestParams,
	  onSuccess,
	  onError
	);
}
/**
 * This function will be called once the Routing REST API provides a response
 * @param  {Object} result          A JSONP object representing the calculated route
 *
 * see: http://developer.here.com/rest-apis/documentation/routing/topics/resource-type-calculate-route.html
 */
function onSuccess(result) {
  var route = result.response.route[0];
 /*
  * The styling of the route response on the map is entirely under the developer's control.
  * A representitive styling can be found the full JS + HTML code of this example
  * in the functions below:
  */
  addRouteShapeToMap(route);
  //addManueversToMap(route);

  //addWaypointsToPanel(route.waypoint);
  //addManueversToPanel(route);
  addSummaryToPanel(route.summary);
  // ... etc.
}

/**
 * This function will be called if a communication error occurs during the JSON-P request
 * @param  {Object} error  The error message received.
 */
function onError(error) {
	alert('Can\'t reach the remote server');
}

// Now use the map as required...
calculateRouteFromAtoB (platform);
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/here.routing.tnl.js"></script>
<%@ include file="../common/footer.jspf"%>