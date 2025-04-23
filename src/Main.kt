/**
 * ====================================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ------------------------------------------------------------------------------------
 * Project Name:   PROJECT NAME HERE
 * Project Author: Harry Stringer
 * GitHub Repo:    https://github.com/waimea-hwstringer/level-3-programming-assessment
 * ------------------------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * ====================================================================================
 */



import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*
import kotlin.system.exitProcess


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
    TutorialPopup().isVisible = true
}

class Room(
    val name: String,
    var immediateContents: String?,
    var secretContents: String?,
    val desc: String,
) {
    var north: Room? = null
    var east: Room? = null
    var south: Room?= null
    var west: Room? = null
    var searched = false
}



fun setupSubmarine(): Room {
    val sea = Room("The Sea","way out",null,"The Triton submarine looms ahead. There is a gaping tear in the hull that you can squeeze into. Return here after you've finished exploring the wreck, so you can ascend to the surface.")
    val entrance = Room("Main Entrance",null,"shoe","There appear to be three hallways ahead. This probably isn't the actual entrance to the sub, but the tear in the hull makes it a convenient entrance for you. You can go back through the hole to your south if you'd like to return to the surface.")
    val westCorridor = Room("West Corridor","screw",null,"This corridor continues to the west and east. There is a door to the north that has a ⚡ symbol on it.")
    val electrical = Room("Electrical","component","computer","Wires and circuit boards are everywhere. Many wires are tangled together in giant knots and balls. Most of the equipment seems to be in utter disarray.")
    val storage = Room("General Storage","oxygen tank","unopened crate","There are many full crates in this room, but most seem to heavy and big to carry. There's a corridor to the east, and two rooms to the north and west. Strangely, the northern room seems to be locked. Maybe there is a key somewhere?")
    val secStorage = Room("Secure Storage","locked chest ✪","small box","You use the key that you found in the Captain's Quarters to unlock the Secure Storage. These items are most likely the most valuable on the vessel.")
    val moonpool = Room("Moon pool","way out",null,"There is a hole in the floor that used to be used to deploy small vessels & ROVs. You could probably jump through the pool and swim out to your submersible if you're lost.")
    val eastCorridor = Room("East Corridor",null,null,"There is a door to the north that reads 'CABIN ALPHA'. It most likely housed some of the crew. The corridor continues to the east.")
    val eastCorridor2 = Room("Far E. Corridor","wristwatch",null,"The door to the north reads 'CABIN BRAVO', to the east is 'CABIN CHARLIE'. A corridor continues to the west.")
    val crew1 = Room("Cabin Alpha","family photo","wedding ring","Barracks for some of the crew. Sleeps six. The room is decorated with photos of family members on the wall, but other than that it contains nothing of note.")
    val crew2 = Room("Cabin Bravo","band poster","CD","Barracks for some of the crew. Sleeps six. The people in this room seemed to be pretty keen on music. The walls are dotted with band posters.")
    val crew3 = Room("Cabin Charlie",null,"toothpaste","A smaller barracks for some of the crew. Sleeps four. This room is a bit cramped, it must have sucked to sleep here.")
    val northCorridor = Room("North Corridor",null,null,"This corridor appears rather long. It continues ahead. To the west there is a door marked with a ✚ symbol. There is an unmarked door to the east.")
    val northCorridor2 = Room("Far N. Corridor","extinguisher",null,"The corridor continues up some stairs to the north. West there is a door with: ⚠ WARNING ⚠ GLOVES & FACE COVERS MUST BE WORN. To the east is an unmarked door.")
    val upperDeck = Room("Upper Deck",null,null,"This corridor seems to be more secure than the others. It continues through a heavy door to the north, and stairs to the south. There are two rooms to the east & west.")
    val bridge = Room("The Bridge",null,null,"This is where the main controls for the sub are. You can see the dark abyss that is the sea through a small porthole to the north. The captain's quarters are probably near by.")
    val captain = Room("Captain's quarters","key","lock box","This is where the captain stayed. It is very cushy and comfortable compared to everywhere else in the vessel. This room is definitely worth searching further.")
    val messHall = Room("Mess Hall","crew roster ✪",null,"There are two big tables in the centre of this room. This is where the crew would come to eat. There is a pot on the flower that probably used to contain a plant.")
    val kitchen = Room("Kitchen","silverware",null,"This is where all the food for the crew was cooked. You can't tell the difference between the bench tops and the ovens because of the sheer amount of rust and detritus.")
    val recRoom = Room("Rec-Room","dartboard","biscuit tin","This room looks much more cozy than the other rather cold rooms. There looks to be a dartboard on the wall, as well as a couple of couches.")
    val comms = Room("Communications","keyboard","hard drive ✪","Big screens coat the walls of this room. There are also many wires and computers on some tables. This is most likely where the Triton would broadcast its location and information back to HQ.")
    val engine = Room("Engine Room","wrench","toolbox","A great rusted mass can be seen in centre of this room. It seems to be what's left of the engine.")
    val lab = Room("Laboratory","microscope","vile of chemicals","This appears to be some kind of lab. There are glass viles and beakers. Most seem to be smashed. There is a large and very heavy crate with a large ☢ sign on it. Best to leave that.")
    val ballast = Room("The Ballast","damaged pipe ✪","bent wrench","There are several large tanks in the middle of this room, with a mess of pipes tangling from the ceiling. It appears that many of the pipes imploded. This may be the reason that the submarine sunk, as the ballast is crucial for keeping the vessel controllable.")
    val medical = Room("Medical Bay","first aid kit","strange syringe ✪","There are a couple of beds in this room, as well as what looks to be an operating table. Various strange utensils are littered on the floor, but they are so rusty they crumble to the touch.")

    sea.north = entrance

    entrance.north = northCorridor
    entrance.east = eastCorridor
    entrance.south = sea
    entrance.west = westCorridor

    westCorridor.north = electrical
    westCorridor.east = entrance
    westCorridor.west = storage

    electrical.south = westCorridor
    electrical.east = lab
    electrical.north = engine

    engine.north = ballast
    engine.east = upperDeck
    engine.south = electrical

    ballast.east = bridge
    ballast.south= engine

    lab.east = northCorridor2
    lab.south = medical
    lab.west = electrical

    medical.north = lab
    medical.east = northCorridor2

    storage.north = secStorage
    storage.east = westCorridor
    storage.west = moonpool

    secStorage.south = storage
    moonpool.east = storage

    eastCorridor.west = entrance
    eastCorridor.east = eastCorridor2
    eastCorridor.north = crew1

    eastCorridor2.west = eastCorridor
    eastCorridor2.north = crew2
    eastCorridor2.east = crew3

    crew1.south = eastCorridor
    crew2.south = eastCorridor2
    crew3.west = eastCorridor2

    northCorridor.north = northCorridor2
    northCorridor.east = messHall
    northCorridor.south = entrance
    northCorridor.west = medical

    northCorridor2.north = upperDeck
    northCorridor2.south = northCorridor
    northCorridor2.east = recRoom
    northCorridor2.west = lab

    upperDeck.north = bridge
    upperDeck.south = northCorridor2
    upperDeck.east = comms
    upperDeck.west = engine

    bridge.south = upperDeck
    bridge.east = captain
    bridge.west = ballast

    captain.west = bridge

    comms.west = upperDeck

    messHall.north = recRoom
    messHall.west = northCorridor
    messHall.east = kitchen
    kitchen.west = messHall
    recRoom.south = messHall
    recRoom.west = northCorridor2

    return sea
}

/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    // Constants defining any key values
    val MAX_OXYGEN = 40

    // Data fields
    var oxygen = MAX_OXYGEN
    var inventory = mutableListOf<String>()
    var importantItems = 0
    var hasKey = false

    // Application logic functions

    var playerLoc: Room

    init {
        playerLoc = setupSubmarine()
    }

    fun movePlayer(direction: String) {
        val nextRoom = when (direction) {
            "north" -> playerLoc.north
            "east" -> playerLoc.east
            "south" -> playerLoc.south
            "west" -> playerLoc.west
            else -> null
        }
        if (nextRoom != null) {
            playerLoc = nextRoom
            oxygen --
        }
    }

    fun search(room: Room) {
        oxygen--
        playerLoc.searched = true
        room.immediateContents = room.secretContents
    }

    fun grab(room: Room) {
        val item = room.immediateContents!!.replaceFirstChar { it.uppercaseChar() }
        useItem(item)
        if(room.immediateContents != "way out") {
            room.immediateContents = null
        }

    }

    fun useItem(item: String) {
        when(item){
            "Oxygen tank" -> oxygen = MAX_OXYGEN
            "Key" -> {hasKey = true; inventory.add(item)}
            "Way out" -> EndPopup(this).isVisible = true
            "Damaged pipe ✪" -> {importantItems++; inventory.add(item)}
            "Crew roster ✪" -> {importantItems++; inventory.add(item)}
            "Strange syringe ✪" -> {importantItems++; inventory.add(item)}
            "Hard drive ✪" -> {importantItems++; inventory.add(item)}
            "Locked chest ✪" -> {importantItems++; inventory.add(item)}
            else -> inventory.add(item)
        }
    }

    fun resetGame() {
        playerLoc = setupSubmarine()
        oxygen = MAX_OXYGEN
        inventory.clear()
    }
}

/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var locName: JLabel
    private lateinit var locDesc: JLabel
    private lateinit var locItems: JLabel
    private lateinit var viewHeader: JLabel

    private lateinit var searchButton: JButton
    private lateinit var upButton: JButton
    private lateinit var grabButton: JButton
    private lateinit var leftButton: JButton
    private lateinit var downButton: JButton
    private lateinit var rightButton: JButton

    private lateinit var o2Symbol: JLabel //Oxygen symbol
    private lateinit var o2bg: JLabel     //Oxygen bar background
    private lateinit var o2fg: JLabel     //Oxygen bar foreground
    private lateinit var inventory: JLabel
    private lateinit var iHeader: JLabel
    private lateinit var tutorialButton: JButton

    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addControls()                   // Build the UI

        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible

        updateView()                    // Initialise the UI
    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "The Triton - Treasure Seeker"
        contentPane.preferredSize = Dimension(1200, 500)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val headerFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 24)

        //---Text & Info
        locName = JLabel("Name of location")
        locName.horizontalAlignment = SwingConstants.CENTER
        locName.bounds = Rectangle(20, 20, 390, 100)
        locName.font = headerFont
        locName.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)
        add(locName)

        locDesc = JLabel("Description of location")
        locDesc.horizontalAlignment = SwingConstants.LEFT
        locDesc.verticalAlignment = SwingConstants.TOP
        locDesc.bounds = Rectangle(20, 140, 390, 340)
        locDesc.font = baseFont
        locDesc.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)
        add(locDesc)

        viewHeader = JLabel("Observations")
        viewHeader.horizontalAlignment = SwingConstants.CENTER
        viewHeader.bounds = Rectangle(430, 20, 340, 62)
        viewHeader.font = headerFont
        viewHeader.background = Color(78, 80, 82)
        viewHeader.isOpaque = true
        add(viewHeader)

        locItems = JLabel("Items in room")
        locItems.horizontalAlignment = SwingConstants.CENTER
        locItems.bounds = Rectangle(430, 80, 340, 160)
        locItems.font = baseFont
        locItems.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)

        add(locItems)

        //---Navigation buttons
        searchButton = JButton("\uD83D\uDC41")
        searchButton.bounds = Rectangle(430,260,100,100)
        searchButton.font = headerFont
        searchButton.addActionListener(this)     // Handle any clicks
        add(searchButton)

        upButton = JButton("↑")
        upButton.bounds = Rectangle(550,260,100,100)
        upButton.font = headerFont
        upButton.addActionListener(this)     // Handle any clicks
        add(upButton)

        grabButton = JButton("\uD83D\uDD91")
        grabButton.bounds = Rectangle(670,260,100,100)
        grabButton.font = headerFont
        grabButton.addActionListener(this)     // Handle any clicks
        add(grabButton)

        leftButton = JButton("←")
        leftButton.bounds = Rectangle(430,380,100,100)
        leftButton.font = headerFont
        leftButton.addActionListener(this)     // Handle any clicks
        add(leftButton)

        downButton = JButton("↓")
        downButton.bounds = Rectangle(550,380,100,100)
        downButton.font = headerFont
        downButton.addActionListener(this)     // Handle any clicks
        add(downButton)

        rightButton = JButton("→")
        rightButton.bounds = Rectangle(670,380,100,100)
        rightButton.font = headerFont
        rightButton.addActionListener(this)     // Handle any clicks
        add(rightButton)

        //---Oxygen & inventory
        o2Symbol = JLabel("O₂")
        o2Symbol.horizontalAlignment = SwingConstants.CENTER
        o2Symbol.bounds = Rectangle(790, 20, 100, 100)
        o2Symbol.font = headerFont
        o2Symbol.foreground = Color(0,0,0)
        add(o2Symbol)

        o2fg = JLabel()
        o2fg.horizontalAlignment = SwingConstants.CENTER
        o2fg.bounds = Rectangle(790, 20, 100, 0)  // Adjust bar position & height
        o2fg.font = headerFont
        o2fg.background = Color(175,175,175)
        o2fg.isOpaque = true
        add(o2fg)

        o2bg = JLabel()
        o2bg.horizontalAlignment = SwingConstants.CENTER
        o2bg.bounds = Rectangle(790, 20, 100, 460)
        o2bg.font = headerFont
        o2bg.border = BorderFactory.createLineBorder(Color(175,175,175), 8) // Add a border
        o2bg.isOpaque = true
        o2bg.background = Color(100,100,255)
        add(o2bg)

        iHeader = JLabel("\uD83C\uDF92 Inventory")
        iHeader.horizontalAlignment = SwingConstants.CENTER
        iHeader.bounds = Rectangle(910, 20, 270, 62)
        iHeader.font = headerFont
        iHeader.background = Color(175,175,175)
        iHeader.foreground = Color(0,0,0)
        iHeader.isOpaque = true
        add(iHeader)

        inventory = JLabel()
        inventory.horizontalAlignment = SwingConstants.LEFT
        inventory.verticalAlignment = SwingConstants.TOP
        inventory.bounds = Rectangle(0, 0, 270, 320)
        inventory.font = baseFont

        val inventoryScroll = JScrollPane(inventory)
        inventoryScroll.bounds = Rectangle(910, 80, 270, 320)
        inventoryScroll.border = BorderFactory.createLineBorder(Color(175,175,175), 8) // Add a border
        inventoryScroll.horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        add(inventoryScroll)

        tutorialButton = JButton("ⓘ Information")
        tutorialButton.bounds = Rectangle(910,420,270,60)
        tutorialButton.font = baseFont
        tutorialButton.addActionListener(this)     // Handle any clicks
        add(tutorialButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {

        locName.text = app.playerLoc.name
        locDesc.text = "<html> <div style='padding: 8px;'> ${app.playerLoc.desc} </div> </html>"

        val o2Height = (app.MAX_OXYGEN - app.oxygen) * 450 / app.MAX_OXYGEN
        o2fg.bounds = Rectangle(790, 25, 100, o2Height)

        if (app.oxygen == 0) {
            EndPopup(app).isVisible = true
        }

        val playerLoc = app.playerLoc
        upButton.isEnabled = playerLoc.north != null
        leftButton.isEnabled = playerLoc.west != null
        downButton.isEnabled = playerLoc.south != null
        rightButton.isEnabled = playerLoc.east != null

        searchButton.isEnabled = !app.playerLoc.searched

        if (app.playerLoc.immediateContents != null) {
            locItems.text = "<html> You can see a ${app.playerLoc.immediateContents}. </html>."
            grabButton.isEnabled = true
            searchButton.isEnabled = false
        }
        else {
            locItems.text = "<html>This room looks empty."
            grabButton.isEnabled = false
        }

        inventory.text = """
            <html>
                <div style='padding: 10px;'>
                    ${app.inventory.mapIndexed { index, item -> "${index + 1}. $item" }.joinToString("<br>")}
                </div>
        """.trimIndent()

        if(app.playerLoc.name == "General Storage" && !app.hasKey) {
            upButton.text = "\uD83D\uDD12"
            upButton.isEnabled = false
        }
        else {
            upButton.text = "↑"
        }
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            upButton -> {
                app.movePlayer("north")
                updateView()
            }
            rightButton -> {
                app.movePlayer("east")
                updateView()
            }
            downButton -> {
                app.movePlayer("south")
                updateView()
            }
            leftButton -> {
                app.movePlayer("west")
                updateView()
            }
            searchButton -> {
                app.search(app.playerLoc)
                updateView()
            }
            grabButton -> {
                app.grab(app.playerLoc)
                updateView()
            }
            tutorialButton -> {
                TutorialPopup().apply {
                    updateView()
                    isVisible = true
                }
            }
        }
    }
}



class TutorialPopup(): JDialog() {
    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * Set up the dialog window
     */
    private fun configureWindow() {
        title = "The Triton - Tutorial"
        contentPane.preferredSize = Dimension(500, 520)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * Populate the window with controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)

        // Adding <html> to the label text allows it to wrap
        val message = JLabel("<html>Welcome to the Triton, diver. This wreck of a submarine has been sitting on the seafloor for a while now. It's your job to check it for valuables, and any clues as to why she sunk. <br> <br> You can use the arrow buttons to move around the submarine. Each move will take up some of your O₂. Make sure you don't get lost, and remember your way around. <br> <br> At the top of the screen you will see any items in the room that are immediately obvious to you. Use the \uD83D\uDD91 button to grab any items there. Items of particular importance will be marked with a ✪ symbol. These are the items that we really want. If you can see 'a way out' using the grab button will exit the submarine. <br> <br> If the room looks empty, you may search the room using the \uD83D\uDC41 button. Any items that you find in the room will then appear in your observations. Be careful, because searching rooms will also take up some of your O₂. <br> <br> Any items that you collect will be stored in your inventory \uD83C\uDF92. Some items may be relevant to helping you explore. <br> <br> There's no telling what you might find in there diver. Good luck. </html>")
        message.bounds = Rectangle(25, 25, 450, 500)
        message.verticalAlignment = SwingConstants.TOP
        message.font = baseFont
        add(message)
    }
}


class EndPopup(val app: App): JDialog(), ActionListener {

    private lateinit var quitButton: JButton
    private lateinit var restartButton: JButton

    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * Set up the dialog window
     */
    private fun configureWindow() {
        title = "The Triton - Ending"
        contentPane.preferredSize = Dimension(500, 350)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * Populate the window with controls
     */
    private fun addControls() {

        val headerFont = Font(Font.SANS_SERIF, Font.PLAIN, 24)

        val headerText :String
        if (app.oxygen <= 0) {
            headerText = "You ran out of oxygen."
        }
        else {
            headerText = "Hello there diver!"
        }
        val header = JLabel(headerText)
        header.bounds = Rectangle(35, 25, 440, 500)
        header.verticalAlignment = SwingConstants.TOP
        header.font = headerFont
        add(header)

        quitButton = JButton("Quit")
        quitButton.bounds = Rectangle(25, 275, 200, 50)
        quitButton.font = headerFont
        quitButton.addActionListener(this)     // Handle any clicks
        add(quitButton)

        restartButton = JButton("Replay")
        restartButton.bounds = Rectangle(275, 275, 200, 50)
        restartButton.font = headerFont
        restartButton.addActionListener(this)     // Handle any clicks
        add(restartButton)

        val text: String
        val no = app.inventory.size //number of total items in inventory
        val noImp = app.importantItems //number of important items in inventory

        if (app.oxygen <= 0){ //dying
            text = "Your vision fades as your last breath escapes. The cold silence of the deep claims another explorer. Maybe one day someone else will figure out what sunk the Triton."
        }
        else if(app.inventory.isEmpty() && app.oxygen > app.MAX_OXYGEN/3){
            text = "Why are you back so soon? You haven't found any items yet. Only grab the 'way out' when you're ready to leave. <br><br> Now get back in there!"
        }
        else if(app.inventory.isEmpty()) {
            text = "You're back empty handed, and nearly out of O2. Unfortunate that we won't find out how the Triton sunk. I'll take us back."
        }
        else if(noImp==0){ //no important items, but at least something
            text = "Welcome back diver. You don't seem to have found any items that will help us figure out why the Triton sunk, but it's nice to have some items to show for our efforts."
        }
        else if (noImp==1||noImp==2){
            text = "Glad to see you back diver, I was getting worried. We have a small amount of information about what happened here now. Good job. It's a shame you didn't find more though."
        }
        else if (noImp==3||noImp==4){
            text = "Glad to see you back diver, I was getting worried. With these items, we should be able to get a fair amount of information about what happened here. If you're ready to leave, we can ascend to the surface."
        }
        else { // noImp == 5
            text = "Glad to see you back diver, I was getting worried. Excellent job! These items should greatly aid us in discovering what happened to this wreck. If you're ready to leave, we can ascend to the surface."
        }


        val message = JLabel("<html>$text</html>")
        message.bounds = Rectangle(35, 70, 440, 500)
        message.verticalAlignment = SwingConstants.TOP
        message.font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        add(message)

        val stats = JLabel("$no/30 items discovered  •  $noImp/5 important ✪ items found")
        stats.bounds = Rectangle(25, 200, 450, 50)
        stats.verticalAlignment = SwingConstants.CENTER
        stats.horizontalAlignment = SwingConstants.CENTER
        stats.font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        add(stats)
    }

    /**
     * Handle UI actions such as button clicks
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            quitButton -> exitProcess(0) //quits program
            restartButton -> {app.resetGame(); dispose()} //resets game & closes popup
        }
    }
}
