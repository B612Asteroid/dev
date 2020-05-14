import React, {Component} from "react";
import axios from "axios";
import DateUtils from "./DateUtils";

class MainComponent extends Component {

    constructor (props) {
        super(props); 
        this.state = {
            message : "",
            lckMatches : []
        }

    }

    componentDidMount() {
        this.getApi();
    }

    getApi = function () {
        axios.get("http://localhost/lck/getLck/2020/Spring")
            .then(function(response) {
                let data = response.data;
                this.setState({
                    lckMatches : data
                });
            }.bind(this))
            .catch(function(response) {
                console.log(response);
                this.setState = ({
                    message : response
                });
            });
    }

    

    render() {
        let lcks = this.state.lckMatches;
        lcks = lcks.map(function(lck, idx) {
            //let parsedDate = DateUtils.dateFormat(new Date(lck.gameDate));
            let parsedDate = DateUtils.format(new Date(lck.gameDate), "yyyy-MM-dd HH:mm:ss");
            return <tr key={idx}>
                <td>{parsedDate}</td>
                <td>{lck.winTeam.teamName}</td>
                <td>{lck.result}</td>
                <td>{lck.loseTeam.teamName}</td>
             </tr>  
         });  
          return(
              <div>
               <div> LCK Matches Result</div>
               <div>
                <table>
                    <tbody>
                        {lcks}
                    </tbody>
                </table>    
               </div> 
             </div>
        ) 
    } 

} 

export default MainComponent
