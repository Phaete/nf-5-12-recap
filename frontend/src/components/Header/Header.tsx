import {Link} from "react-router-dom";
import './Header.css'

export default function Header() {
	return (
		<>
			<h1>To-Do-App</h1>
			<div>
				Navigation:
				<div className={"navBar"}>
					<button type={"button"} className={"link"}>
						<Link to={"/"}>Home</Link>
					</button>
					<button type={"button"} className={"link"}>
						<Link to={"/open"}>Open</Link>
					</button>
					<button type={"button"} className={"link"}>
						<Link to={"/in_progress"}>In Progress</Link>
					</button>
					<button type={"button"} className={"link"}>
						<Link to={"/done"}>Done</Link>
					</button>
				</div>
			</div>
		</>
	)
}