import {Link} from "react-router-dom";
import './Header.css'

export default function Header() {
	return (
		<>
			<h1>To-Do-App</h1>
			<div>
				Navigation:
				<div className={"navBar"}>
					<Link to={"/"}>
						<button type={"button"} className={"link"}>
							Home
						</button>
					</Link>
					<Link to={"/open"}>
						<button type={"button"} className={"link"}>
							Open
						</button>
					</Link>
					<Link to={"/in_progress"}>
						<button type={"button"} className={"link"}>
							In Progress
						</button>
					</Link>
					<Link to={"/done"}>
						<button type={"button"} className={"link"}>
							Done
						</button>
					</Link>
				</div>
			</div>
		</>
	)
}