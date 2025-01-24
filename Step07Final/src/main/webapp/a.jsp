<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
  <div id="calendar"></div>

  <!-- Modal -->
  <div class="modal fade" id="eventModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">이벤트 추가</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="eventForm">
            <div class="mb-3">
              <label for="eventName" class="form-label">이름</label>
              <input type="text" class="form-control" id="eventName" required>
            </div>
            <div class="mb-3">
              <label for="eventDate" class="form-label">날짜</label>
              <input type="text" class="form-control" id="eventDate" readonly>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" id="saveEvent">저장</button>
        </div>
      </div>
    </div>
  </div>

  <script>
    document.addEventListener('DOMContentLoaded', function() {
      var calendarEl = document.getElementById('calendar');
      var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        dateClick: function(info) {
          // 날짜 클릭 시 모달 띄우기
          document.getElementById('eventDate').value = info.dateStr;
          var modal = new bootstrap.Modal(document.getElementById('eventModal'));
          modal.show();
        }
      });
      calendar.render();

      // 이벤트 저장 버튼 클릭
      document.getElementById('saveEvent').addEventListener('click', function() {
        var eventName = document.getElementById('eventName').value;
        var eventDate = document.getElementById('eventDate').value;

        if (eventName && eventDate) {
          calendar.addEvent({
            title: eventName,
            start: eventDate
          });
          bootstrap.Modal.getInstance(document.getElementById('eventModal')).hide();
        }
      });
    });
  </script>
</body>
</html>
