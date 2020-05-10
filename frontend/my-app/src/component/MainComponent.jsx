import React, {Component} from "react";
import axios from "axios";

class MainComponent extends Component {

    constructor (props) {
        super(props); 
        this.state = {
            message : ""
        }

    }

    componentDidMount() {
        this.getApi();
    }

    getApi = function () {
        axios.get("http://localhost/lck/getLck/2020/Spring")
            .then(function(response) {
                console.log(response.data);
                /*
                this.setState({
                    message : response.data
                });
                */
            }.bind(this))
            .catch(function(response) {
                console.log(response);
            });
    }
     render() {

          return(
              <div>
               <div> Main 페이지 </div>
               <div>{/*this.state.message*/}</div> 
             </div>
        ) 
    } 

} 

export default MainComponent
