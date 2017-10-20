<h1>MONITOR</h1>

<c:if test="${fn:length(historyList) gt 0}">
	<div>
	
		<table style="border: 1px solid black; text-align: center;">
			<thead><th>Date</th><th>Machine ID</th><th>CPU</th><th>RAM</th><th>Storage</th></thead>
			<tbody>
				<c:forEach items="${historyList}" var="historyLine">
					<tr><td>${historyLine.dateEvent}</td><td>${historyLine.machineId}</td><td <c:if test="${historyLine.cpuState == VERYGOOD}">style="color: #1ed000;"</c:if>>{historyLine.cpuState}<td>${historyLine.ramState}</td><td>${historyLine.storageState}</td></tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
</c:if>