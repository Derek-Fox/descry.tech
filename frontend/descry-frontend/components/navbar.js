import Image from "next/image";
import eye from "@/public/eye.png";
import styles from "@/styles/navbar.module.css";

export default function Navbar() {
    return (
        <div>
            <div className={styles.header} id="home">
                <div className={styles.titleBox}>
                    <div className={styles.title}>
                        <h1>Descry</h1>
                    </div>
                    <div className={styles.subtitle}>
                        <h2>Algorithms Visualized</h2>
                    </div>
                </div>

                <Image
                    src={eye}
                    alt="Image of an eye"
                    width={150}
                    height={150}
                />
            </div>

            <nav className={styles.navbar}>
                <ul className={styles.menus}>
                    <li><a href="#about">About</a></li>
                    <li><a href="#home">Home</a></li>
                    <li><a href="#how-to-use">How-to-use</a></li>
                </ul>
            </nav>
        </div>
    );
}