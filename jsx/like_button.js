const domContainer = document.getElementById('root');
var index = 0;

function Time (props) {
    return <h2 class={props.className}>{new Date().toLocaleTimeString()}</h2>;
  }

function tick() {
    index++;
    let time
    if (index%2==0) {time = <Time className="label"/>;}
    else {time = <Time className="label-red"/>;}
    ReactDOM.render(time, domContainer);
  }
  
  setInterval(tick, 1000);