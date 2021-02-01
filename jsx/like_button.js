class Clock extends React.Component {
  constructor(props) {
    super(props);
    this.state = { date: new Date(), activeClass: "label-red" };
  }

  componentDidMount() {
    this.timerID = setInterval(() => this.tick(), 1000);
  }

  componentWillUnmount() {
    clearInterval(this.timerID);
  }

  tick() {
    if (this.state.activeClass == "label-red") {
      this.setState({ activeClass: "label-green"});
    }
    else {
      this.setState({ activeClass: "label-red" });
    }

    this.setState({
      date: new Date()
    });
  }

  render() {
    return <h2 className={this.state.activeClass}>{this.state.date.toLocaleTimeString()}</h2>;
  }
}

const domContainer = document.getElementById("root");
ReactDOM.render(<Clock />, domContainer);
