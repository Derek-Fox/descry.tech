import Image from "next/image";
import eye from "@/public/eye.png";
import styles from "@/styles/navbar.module.css";
import Link from 'next/link'

export default function Navbar() {
    return (
        <div>
            <Link href="/" className={styles.header} id="home">
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
            </Link>

            <nav className={styles.navbar}>
                <ul className={styles.menus}>
                    <li><a href="/" >Home</a></li>
                    <li><a href="/#about">About</a></li>
                    <li><a href="/#usage">Usage</a></li>
                </ul>
            </nav>
        </div>
    );
}