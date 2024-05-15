// scripts.js
$('.memoModal .close_btn').click(function () {
    $('.memoModal').css('display', 'none');
});
$('.eventModal .close_btn').click(function () {
    $('.eventModal').css('display', 'none');
});
var calendar;
document.addEventListener('DOMContentLoaded', function () {
    $(function () {
        axios.get('/data/all')
            .then(function (response) {
                var data = response.data.events;
                var memos = response.data.memos;
                var infoRow = document.querySelector('.info_row');
                data.forEach(event => {
                    var newRow = document.createElement('div');
                    newRow.classList.add('info_row');
                    var colorLabel = document.createElement('label');
                    colorLabel.classList.add('label');
                    colorLabel.style.backgroundColor = event.eventColor;
                    colorLabel.style.color = '#fff';
                    newRow.appendChild(colorLabel);
                    var eventLabel = document.createElement('label');
                    eventLabel.classList.add('label');
                    eventLabel.textContent = event.content;
                    newRow.appendChild(eventLabel);
                    var startDateLabel = document.createElement('label');
                    startDateLabel.classList.add('label');
                    let syear = event.startDate[0];
                    let smonth = event.startDate[1];
                    let sday = event.startDate[2];
                    let sformattedDate = `${syear}.${smonth.toString().padStart(2, '0')}.${sday.toString().padStart(2, '0')}`;
                    startDateLabel.textContent = sformattedDate;
                    newRow.appendChild(startDateLabel);
                    var endDateLabel = document.createElement('label');
                    endDateLabel.classList.add('label');
                    let eyear = event.endDate[0];
                    let emonth = event.endDate[1];
                    let eday = event.endDate[2];
                    let eformattedDate = `${eyear}.${emonth.toString().padStart(2, '0')}.${eday.toString().padStart(2, '0')}`;
                    endDateLabel.textContent = eformattedDate;
                    newRow.appendChild(endDateLabel);
                    var cycleLabel = document.createElement('label');
                    cycleLabel.classList.add('label');
                    cycleLabel.textContent = event.cycle;
                    newRow.appendChild(cycleLabel);
                    var deleteLabel = document.createElement('label');
                    deleteLabel.classList.add('label');
                    deleteLabel.textContent = '삭제';
                    deleteLabel.style.color = "black";
                    deleteLabel.style.cursor = "pointer";
                    const eventId = event.eventId;
                    deleteLabel.onclick = function () {
                        axios.post(`/delete/${eventId}`)
                            .then(response => {
                                console.log('Event deleted:', response.data);
                                newRow.parentNode.removeChild(newRow);
                            })
                            .catch(error => {
                                console.error('Error deleting event', error);
                                alert('Failed to delete event: ' + error.message);
                            });
                    };
                    newRow.appendChild(deleteLabel);
                    infoRow.parentNode.insertBefore(newRow, infoRow.nextSibling);
                });
                console.log(data);
                console.log(memos);
                var calendarEl = document.getElementById('calendar');
                calendar = new FullCalendar.Calendar(calendarEl, {
                    height: '700px',
                    expandRows: true,
                    slotMinTime: '08:00',
                    slotMaxTime: '20:00',
                    customButtons: {
                        myCustomButton: {
                            text: "일정 추가하기",
                            click: function () {
                                const modal = document.querySelector('.eventModal');
                                modal.style.display = "flex";
                            }
                        },
                        myMemoButton: {
                            text: "메모 추가하기",
                            click: function () {
                                const modal = document.querySelector('.memoModal');
                                modal.style.display = "flex";
                            }
                        },
                    },
                    headerToolbar: {
                        left: 'prev,next today,myCustomButton',
                        center: 'title',
                        right: ''
                    },
                    initialView: 'dayGridMonth',
                    editable: true,
                    dayMaxEvents: true,
                    locale: 'ko',
                });
                data.forEach(function (event) {
                    const start = new Date(event.startDate[0], event.startDate[1] - 1, event.startDate[2], event.startDate[3], event.startDate[4]);
                    const end = new Date(event.endDate[0], event.endDate[1] - 1, event.endDate[2], event.endDate[3], event.endDate[4]);
                    function addEvent(start, end) {
                        calendar.addEvent({
                            title: event.content,
                            start: start,
                            end: end,
                            backgroundColor: event.eventColor
                        });
                    }
                    addEvent(start, end);
                    if (event.cycle > 0) {
                        const cycleDays = event.cycle;
                        let currentDate = new Date(start);
                        let currentEndDate = new Date(end);
                        let someYearLimit = event.startDate[0] + 10;
                        while (currentDate.getFullYear() < someYearLimit) {
                            currentDate.setDate(currentDate.getDate() + cycleDays);
                            currentEndDate.setDate(currentEndDate.getDate() + cycleDays);
                            addEvent(new Date(currentDate), new Date(currentEndDate));
                        }
                    }
                });
                memos.forEach(function (memo) {
                    const start = new Date(memo.memoDate[0], memo.memoDate[1] - 1, memo.memoDate[2]);
                    const end = new Date(memo.memoDate[0], memo.memoDate[1] - 1, memo.memoDate[2]);
                    function addEvent(start, end) {
                        calendar.addEvent({
                            title: "오늘의 메모 :  " + memo.content,
                            start: start,
                            end: end,
                            allDay: true,
                            display: 'auto',
                            backgroundColor: 'white',
                            textColor: 'black',
                            className: 'custom-event-style'
                        });
                    }
                    addEvent(start, end);
                });
                calendar.render();
                calendar.on('dateClick', function (info) {
                    const modal = document.querySelector('.memoModal');
                    const memoDateSpan = document.querySelector('.memoDate_span');
                    const memoDateInput = document.querySelector('#memoDate');
                    const dateClicked = info.dateStr;
                    const dbDateClicked = info.date.toISOString();  // "YYYY-MM-DDTHH:mm:ss.sssZ" 형식

                    // 필요한 경우 다른 형식으로 변환 (예: "YYYY-MM-DDTHH:mm:ss")
                    const formattedDateClicked = dbDateClicked.slice(0, 19);
                    memoDateInput.value = formattedDateClicked;
                    memoDateSpan.innerText = dateClicked;
                    modal.style.display = "flex";
                    function formatDateString(dateString) {
                        if (typeof dateString !== 'string') {
                            dateString = dateString.toString();
                        }
                        const [year, month, day] = dateString.split(',').map(Number);
                        const formattedMonth = month.toString().padStart(2, '0');
                        const formattedDay = day.toString().padStart(2, '0');
                        return `${year}-${formattedMonth}-${formattedDay}`;
                    }
                    function parseDateArray(dateArray) {
                        return `${dateArray[0]}-${(dateArray[1] ).toString().padStart(2, '0')}-${dateArray[2].toString().padStart(2, '0')}`;
                    }
                    var memoInfoRow = document.querySelector('.today_memo');
                    memos.forEach(event => {
                        const formattedDate = formatDateString(event.memoDate);
                        if (formattedDate === dateClicked) {
                            console.log("오늘은 며칠" + event.memoDate);
                            var newRow = document.createElement('div');
                            newRow.classList.add('today_memo');
                            var eventLabel = document.createElement('label');
                            eventLabel.classList.add('label');
                            eventLabel.textContent = event.content;
                            newRow.appendChild(eventLabel);
                            var deleteLabel = document.createElement('label');
                            deleteLabel.classList.add('label');
                            deleteLabel.textContent = 'x';
                            deleteLabel.style.color = "black";
                            deleteLabel.style.cursor = "pointer";
                            const memoId = event.memoId;
                            deleteLabel.onclick = function () {
                                axios.post(`/memoDelete/${memoId}`)
                                    .then(response => {
                                        console.log('Event deleted:', response.data);
                                        newRow.parentNode.removeChild(newRow);
                                    })
                                    .catch(error => {
                                        console.error('Error deleting event', error);
                                        alert('Failed to delete event: ' + error.message);
                                    });
                            };
                            newRow.appendChild(deleteLabel);
                            memoInfoRow.parentNode.insertBefore(newRow, memoInfoRow.nextSibling);
                        }
                    });
                    var eventInfoRow = document.querySelector('.today_event');
                    data.forEach(event => {
                        const startDate = parseDateArray(event.startDate);
                        const endDate = parseDateArray(event.endDate);
                        console.log("start" + startDate);
                        console.log("click" + dateClicked);
                        if (dateClicked >= startDate && dateClicked <= endDate) {
                            var newRow = document.createElement('div');
                            newRow.classList.add('today_event');
                            var colorLabel = document.createElement('label');
                            colorLabel.classList.add('label');
                            colorLabel.style.backgroundColor = event.eventColor;
                            colorLabel.style.color = '#fff';
                            newRow.appendChild(colorLabel);
                            var eventLabel = document.createElement('label');
                            eventLabel.classList.add('label');
                            eventLabel.textContent = event.content;
                            newRow.appendChild(eventLabel);
                            var deleteLabel = document.createElement('label');
                            deleteLabel.classList.add('label');
                            deleteLabel.textContent = 'x';
                            deleteLabel.style.color = "black";
                            deleteLabel.style.cursor = "pointer";
                            const eventId = event.eventId;
                            deleteLabel.onclick = function () {
                                axios.post(`/delete/${eventId}`)
                                    .then(response => {
                                        console.log('Event deleted:', response.data);
                                        newRow.parentNode.removeChild(newRow);
                                    })
                                    .catch(error => {
                                        console.error('Error deleting event', error);
                                        alert('Failed to delete event: ' + error.message);
                                    });
                            };
                            newRow.appendChild(deleteLabel);
                            eventInfoRow.parentNode.insertBefore(newRow, eventInfoRow.nextSibling);
                        }
                    });
                });
            })
            .catch(function (error) {
                console.log(error);
                alert("Request failed: " + error.message);
            });
    });
});
document.getElementById('addEventForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const formData = new FormData(this);
    const eventData = {
        content: formData.get('content'),
        eventColor: formData.get('eventColor'),
        startDate: formData.get('startDate'),
        endDate: formData.get('endDate'),
        cycle: formData.get('cycle')
    };
    axios.post('http://localhost:12000/add', eventData)
        .then(response => {
            console.log('Event added:', response.data);
            calendar.addEvent({
                title: eventData.content,
                start: eventData.startDate,
                end: eventData.endDate,
                backgroundColor: eventData.eventColor
            });
            location.reload();
        })
        .catch(error => {
            console.error('Error adding event', error);
        });
});
document.getElementById('addMemoForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const formData = new FormData(this);
    const memoData = {
        content: formData.get('memoContent'),
        memoDate: formData.get('memoDate')
    };
    console.log("디비테스트"+memoData.memoDate)
    axios.post('http://localhost:12000/memoAdd', memoData)
        .then(response => {
            console.log('Event added:', response.data);
            calendar.addEvent({
                content: memoData.content,
                memo_date: memoData.memoDate
            });
            location.reload();
        })
        .catch(error => {
            console.error('Error adding event', error);
        });
});
