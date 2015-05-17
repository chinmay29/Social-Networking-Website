<%@ include file="../header.jsp"%>
<div class="chat-page">
	<div class="tabpanel-border">
		<ul class="nav nav-tabs ul-list">
			<li class="equal-width"><a
				href="${pageContext.request.contextPath}/chatHistory">History</a></li>
			<li class="equal-width active"><a
				href="${pageContext.request.contextPath}/chatMessenger">Messenger</a></li>
		</ul>
		<div class="tab-content add-tab-border content">
			<div role="tabpanel" class="tab-pane content active"
				id="chatMessenger">
				<div class="div1">
					<div class="span4 content add-bottom-border">
						<a href="${pageContext.request.contextPath}/chat/newChatBox"
							class="btn pull-right add-some-margin"> <i
							class="icon-repeat"></i>&nbsp;&nbsp;Reset ToList
						</a>
					</div>
					<div
						class="span8 content add-bottom-border add-left-border dark-grey-bg">
						<label id="recepientsListChat"> <c:if
								test="${not empty receipients}">
								<c:forEach items="${receipients}" var="receipient">
									<c:if test="${receipient.getUserId() != userId}">
								<c:out value="${receipient.getFirstName()}"></c:out> <c:out value="${receipient.getLastName()}"></c:out>; 
								 </c:if>
								</c:forEach>
							</c:if>
						</label>
					</div>
				</div>
				<div class="div2">
					<div class="span4 content refresh-friends">
						<form id="searchForm" class="search-form" method="post"
							action="${pageContext.request.contextPath}/chat/searchFriend">
							<input id="searchText" type="search" name="searchQuery"
								class="search-box" placeholder="Search User" />
							<button id="searchSubmitBtn" name="searchSubmitBtn"
								class="btn search-button" type="submit">
								<i class="icon-search"></i>
							</button>
						</form>
					</div>
					<div
						class="span8 content add-bottom-border add-left-border light-grey-bg">
						<form id="messageForm" class="message-form" method="post"
							action="${pageContext.request.contextPath}/chat/sendMsg">
							<input id="messageText" type="search" name="message"
								class="message-box" placeholder="Write a message..." />
							<button id="messageSubmitBtn" name="messageSubmitBtn"
								class="btn message-button" type="submit">
								<i class="icon-play"></i>
							</button>
						</form>
					</div>
				</div>
				<div class="in-content">
					<div class="span4 content">
						<c:if test="${not empty matchedFriendsList}">
							<div class="matchedFriendsList">
								<table class="table table-hover matched-table">
									<c:forEach items="${matchedFriendsList}" var="friend">
										<tr>
											<td class="hover-item-style">
											<label class="content"><a
													class="fill-anchor"
													href="${pageContext.request.contextPath}/chat/addUser?friendId=${friend.getUserId()}">
													<i><c:out
															value="+ ${friend.getFirstName()} ${friend.getLastName()}"></c:out></i></a>
											</label></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:if>
					</div>
					<div class="span8 content add-left-border">
						<div>
						<label id="frequentContactsLabel">Frequent Contacts</label>
						</div>
						<div class = "row add-row-margins">
						<c:if test="${not empty frequentContactsList}">
							<div class="frequentContactsList">
								<table class="table table-hover matched-table">
									<c:forEach items="${frequentContactsList}" var="contact">
									<tr>
									<td class="hover-item-style">
									<label class="content">
									<a class="fill-anchor" href="${pageContext.request.contextPath}/chat/addUser?friendId=${contact.getUserId()}">
									<i><c:out value="+ ${contact.getFirstName()} ${contact.getLastName()}"></c:out></i>
									</a>
									</label>
									</td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp"%>