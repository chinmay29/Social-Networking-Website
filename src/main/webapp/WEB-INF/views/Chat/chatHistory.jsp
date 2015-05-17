<%@ include file="../header.jsp"%>

<div class="chat-page">
	<div class="tabpanel-border">
		<ul class="nav nav-tabs ul-list">
			<li class="equal-width active"><a
				href="${pageContext.request.contextPath}/chatHistory">History</a></li>
			<li class="equal-width"><a
				href="${pageContext.request.contextPath}/chatMessenger">Messenger</a></li>
		</ul>
		<div class="tab-content add-tab-border content">
			<div role="tabpanel" class="tab-pane content active" id="chatHistory">
				<div class="div1">
					<div class="span4 content add-bottom-border">
						<a
							href="${pageContext.request.contextPath}/chat/refreshChatHistory"
							class="btn pull-right refresh-history-btn"> <i
							class="icon-refresh"></i>&nbsp;&nbsp;Refresh History
						</a>
					</div>
					<div
						class="span8 content add-bottom-border add-left-border dark-grey-bg">
						<label id="recepientsListHistory"> <c:if
								test="${not empty receipientUserList}">
								<c:forEach items="${receipientUserList}" var="receipientUser">
									<c:if test="${receipientUser.getUserId() != userId}">
							<c:out value="${receipientUser.getFirstName()}"></c:out> <c:out value="${receipientUser.getLastName()}"></c:out>; 
							</c:if>
								</c:forEach>
							</c:if>
						</label>
					</div>
				</div>
				<div class="div2">
					<div class="span4 content refresh-history">
						<label id="chatHistoryLabel">Recent Chat</label>
					</div>
					<div
						class="span8 content add-bottom-border add-left-border light-grey-bg">
						<form id="replyForm" class="message-form" method="post"
							action="${pageContext.request.contextPath}/chat/replyMsg">
							<input id="replyText" type="search" name="message"
								class="message-box" placeholder="Write a message..." />
							<button id="replySubmitBtn" name="replySubmitBtn"
								class="btn message-button" type="submit">
								<i class="icon-play"></i>
							</button>
						</form>
					</div>
				</div>
				<div class="in-content">
					<div class="span4 content">
						<div id="chatGroups">
							<c:if test="${not empty userChats}">
								<table
									class="table table-striped table-bordered table-hover add-table-margin chat-list-table">
									<c:forEach items="${userChats.keySet()}" var="userChatId">
										<tr>
											<td class="hover-item-style"><label id="recepientsChatListHistory"> <a
													class="fill-anchor"
													href="${pageContext.request.contextPath}/chat/refreshChat?chatId=${userChatId}">
													<i><c:forEach
															items="${userChats.get(userChatId)}" var="receipient">
															<c:if test="${receipient.getUserId() != userId}">
								<c:out value="${receipient.getFirstName()}"></c:out> <c:out value="${receipient.getLastName()}"></c:out>;
								 </c:if>
														</c:forEach></i> </a>
											</label></td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
						</div>
					</div>
					<div class="span8 content add-left-border">
						<div id="msgBox" class="receivedMsgs">
							<c:if test="${not empty chatHistoryUserMsgList}">
								<c:forEach items="${chatHistoryUserMsgList}"
									var="chatHistoryUserMsg">
									<c:if test="${chatHistoryUserMsg.getFromUserId() == userId}">
										<div class="row">
											<label class="userMsgs"> <a
												href="${pageContext.request.contextPath}/chat/deleteMsg?chatId=${chatHistoryUserMsg.getChatId()}&msgId=${chatHistoryUserMsg.getMsgId()}&fromUserId=${chatHistoryUserMsg.getFromUserId()}&toUserId=${chatHistoryUserMsg.getToUserId()}"><i
													class="icon-trash"></i></a>&nbsp;&nbsp;<b><c:out value="${chatHistoryUserMsg.getMsgText()}"></c:out></b><br />
												<i>
												<c:out value="${chatHistoryUserMsg.getFirstName()}"></c:out> -
													<c:out value="${chatHistoryUserMsg.getMsgTime()}"></c:out> </i>
											</label>
										</div>
									</c:if>
									<c:if test="${chatHistoryUserMsg.getFromUserId() != userId}">
										<div class="row">
											<label class="otherMsgs"> <b><c:out value="${chatHistoryUserMsg.getMsgText()}"></c:out> </b>&nbsp;&nbsp;
												<a
												href="${pageContext.request.contextPath}/chat/deleteMsg?chatId=${chatHistoryUserMsg.getChatId()}&msgId=${chatHistoryUserMsg.getMsgId()}&fromUserId=${chatHistoryUserMsg.getFromUserId()}&toUserId=${chatHistoryUserMsg.getToUserId()}"><i
													class="icon-trash"></i></a><br /> <i>
													<c:out value="${chatHistoryUserMsg.getFirstName()}"></c:out> - <c:out value="${chatHistoryUserMsg.getMsgTime()}"></c:out> </i>
											</label>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value='/resources/js/chat.js' />"></script>
<%@ include file="../footer.jsp"%>