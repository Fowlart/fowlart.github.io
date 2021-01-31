var domContainer = document.getElementById('root');
var index = 0;

function Time(props) {
    return React.createElement(
        "h2",
        { "class": props.className },
        new Date().toLocaleTimeString()
    );
}

function tick() {
    index++;
    var time = void 0;
    if (index % 2 == 0) {
        time = React.createElement(Time, { className: "label" });
    } else {
        time = React.createElement(Time, { className: "label-red" });
    }
    ReactDOM.render(time, domContainer);
}

setInterval(tick, 1000);