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


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
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
    val sea = Room("Sea",null,null,"The Triton submarine looms ahead. There is a gaping tear in the hull that you can squeeze into. Return here after you've finished exploring the wreck, so you can ascend to the surface.")
    val entrance = Room("Entrance","wrench","amulet","the entrance")
    val westCorridor = Room("West Corridor",null,null,"logistics corridor")
    val electrical = Room("Electrical","damaged component",null,"wires and circuitry")
    val storage = Room("Storage","oxygen tank","unopened crate","the warehouse")
    val secStorage = Room("Secure Storage",null,null,"for valuables. It's locked.")
    val moonpool = Room("Moon pool",null,null,"an exit")
    val eastCorridor = Room("East Corridor",null,null,"crew corridor", )
    val eastCorridor2 = Room("Further East Corridor",null,null,"crew corridor", )
    val crew1 = Room("Crew 1","family photo",null,"crew 1")
    val crew2 = Room("Crew 2","band poster",null,"crew 2")
    val crew3 = Room("Crew 3",null,null,"crew 3")
    val northCorridor = Room("North Corridor",null,null,"main corridor")
    val northCorridor2 = Room("Further North Corridor",null,null,"main corridor")
    val upperDeck = Room("Upper Deck",null,null,"The upper deck")
    val bridge = Room("Bridge",null,null,"the main control room")
    val captain = Room("Captain's quarters","key","lockbox","the captain's quarters")
    val messHall = Room("Mess Hall","crew roster",null,"where you eat")
    val kitchen = Room("Kitchen",null,null,"kitchen")
    val recRoom = Room("Rec Room",null,null,"rec room")
    val comms = Room("Communications","radio",null,"Radio & radar")
    val engine = Room("Engine",null,null,"engine room")
    val lab = Room("Lab",null,null,"chemicals galore")
    val ballast = Room("Ballast",null,null,"ballast")
    val medical = Room("Medical","first aid kit",null,"medical bay")

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
    val MAX_OXYGEN = 60

    // Data fields
    var oxygen = MAX_OXYGEN
    var inventory = mutableListOf<String>()
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
        if (room.name == "Sea") {
            println("You left!")
        }
        else {
            val item = room.immediateContents!!.replaceFirstChar { it.uppercaseChar() }
            useItem(item)
            room.immediateContents = null
        }
    }

    fun useItem(item: String) {
        when(item){
            "Oxygen tank" -> oxygen = MAX_OXYGEN
            "Key" -> {hasKey = true; inventory.add(item)}
            else -> inventory.add(item)
        }
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
    private lateinit var inventoryButton: JButton
    private lateinit var examplePopUp: PopUpDialog

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
        contentPane.preferredSize = Dimension(1000, 500)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)

        //---Text & Info
        locName = JLabel("Name")
        locName.horizontalAlignment = SwingConstants.CENTER
        locName.bounds = Rectangle(20, 20, 290, 100)
        locName.font = baseFont
        locName.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)
        add(locName)

        locDesc = JLabel("Desc")
        locDesc.horizontalAlignment = SwingConstants.LEFT
        locDesc.verticalAlignment = SwingConstants.TOP
        locDesc.bounds = Rectangle(20, 140, 290, 340)
        locDesc.font = Font(Font.SANS_SERIF, Font.PLAIN, 24)
        locDesc.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)
        add(locDesc)

        viewHeader = JLabel("Observations")
        viewHeader.horizontalAlignment = SwingConstants.CENTER
        viewHeader.bounds = Rectangle(330, 20, 340, 62)
        viewHeader.font = baseFont
        viewHeader.background = Color(78, 80, 82)
        viewHeader.isOpaque = true
        add(viewHeader)

        locItems = JLabel("Items in room")
        locItems.horizontalAlignment = SwingConstants.CENTER
        locItems.bounds = Rectangle(330, 80, 340, 160)
        locItems.font = Font(Font.SANS_SERIF, Font.PLAIN, 24)
        locItems.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)

        add(locItems)

        //---Navigation buttons
        searchButton = JButton("\uD83D\uDC41")
        searchButton.bounds = Rectangle(330,260,100,100)
        searchButton.font = baseFont
        searchButton.addActionListener(this)     // Handle any clicks
        add(searchButton)

        upButton = JButton("↑")
        upButton.bounds = Rectangle(450,260,100,100)
        upButton.font = baseFont
        upButton.addActionListener(this)     // Handle any clicks
        add(upButton)

        grabButton = JButton("\uD83D\uDD91")
        grabButton.bounds = Rectangle(570,260,100,100)
        grabButton.font = baseFont
        grabButton.addActionListener(this)     // Handle any clicks
        add(grabButton)

        leftButton = JButton("←")
        leftButton.bounds = Rectangle(330,380,100,100)
        leftButton.font = baseFont
        leftButton.addActionListener(this)     // Handle any clicks
        add(leftButton)

        downButton = JButton("↓")
        downButton.bounds = Rectangle(450,380,100,100)
        downButton.font = baseFont
        downButton.addActionListener(this)     // Handle any clicks
        add(downButton)

        rightButton = JButton("→")
        rightButton.bounds = Rectangle(570,380,100,100)
        rightButton.font = baseFont
        rightButton.addActionListener(this)     // Handle any clicks
        add(rightButton)

        //---Oxygen & inventory
        o2Symbol = JLabel("O₂")
        o2Symbol.horizontalAlignment = SwingConstants.CENTER
        o2Symbol.bounds = Rectangle(690, 20, 100, 100)
        o2Symbol.font = baseFont
        o2Symbol.foreground = Color(0,0,0)
        add(o2Symbol)

        o2fg = JLabel()
        o2fg.horizontalAlignment = SwingConstants.CENTER
        o2fg.bounds = Rectangle(690, 20, 100, 0)  // Adjust bar position & height
        o2fg.font = baseFont
        o2fg.background = Color(175,175,175)
        o2fg.isOpaque = true
        add(o2fg)

        o2bg = JLabel()
        o2bg.horizontalAlignment = SwingConstants.CENTER
        o2bg.bounds = Rectangle(690, 20, 100, 460)
        o2bg.font = baseFont
        o2bg.border = BorderFactory.createLineBorder(Color(175,175,175), 8) // Add a border
        o2bg.isOpaque = true
        o2bg.background = Color(100,100,255)
        add(o2bg)

        iHeader = JLabel("Inventory")
        iHeader.horizontalAlignment = SwingConstants.CENTER
        iHeader.bounds = Rectangle(810, 20, 170, 62)
        iHeader.font = baseFont
        iHeader.background = Color(175,175,175)
        iHeader.foreground = Color(0,0,0)
        iHeader.isOpaque = true
        add(iHeader)

        inventory = JLabel()
        inventory.horizontalAlignment = SwingConstants.LEFT
        inventory.verticalAlignment = SwingConstants.TOP
        inventory.bounds = Rectangle(810, 80, 170, 400)
        inventory.font = Font(Font.SANS_SERIF, Font.PLAIN, 24)
        inventory.border = BorderFactory.createLineBorder(Color(175,175,175), 8) // Add a border
        add(inventory)

        inventoryButton = JButton("\uD83C\uDF92")
        inventoryButton.bounds = Rectangle(810,380,100,100)
        inventoryButton.font = baseFont
        inventoryButton.addActionListener(this)     // Handle any clicks
        add(inventoryButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {

        locName.text = app.playerLoc.name
        locDesc.text = "<html> <div style='padding: 8px;'> ${app.playerLoc.desc} </div> </html>"
        println("Oxygen: ${app.oxygen}" )

        val o2Height = (app.MAX_OXYGEN - app.oxygen) * 450 / app.MAX_OXYGEN
        o2fg.bounds = Rectangle(690, 25, 100, o2Height)

        if (app.oxygen == 0) {
            println("DEAD")
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
            </html>
        """.trimIndent()

        if(app.playerLoc.name == "Storage" && !app.hasKey) {
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
            inventoryButton -> {
                PopUpDialog(app, this).apply {
                    updateView()
                    isVisible = true
                }
            }
        }
    }
}


/**
 * Displays a modal dialog
 * The app data model is passed as an argument so
 * that the model can be accessed, as is the parent
 * window object, so that this can be accessed too
 */
class PopUpDialog(val app: App, val mainWindow: MainWindow): JDialog(), ActionListener {
    private lateinit var popText: JLabel
    private lateinit var popButton: JButton

    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * Setup the dialog window
     */
    private fun configureWindow() {
        title = "The Triton - Your Inventory"
        contentPane.preferredSize = Dimension(400, 500)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * Populate the window with controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)

        popText = JLabel("Your Inventory")
        popText.horizontalAlignment = SwingConstants.CENTER
        popText.bounds = Rectangle(20, 20, 350, 100)
        popText.font = baseFont
        popText.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)
        add(popText)

        popButton = JButton("X")
        popButton.horizontalAlignment = SwingConstants.CENTER
        popButton.bounds = Rectangle(20, 140, 200, 100)
        popButton.font = baseFont
        popButton.border = BorderFactory.createLineBorder(Color(78, 80, 82), 6)
        add(popButton)
    }

    /**
     * Update the view with data from the data model
     */
    fun updateView() {

    }

    /**
     * Handle UI actions such as button clicks
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            popButton -> {
                println("XXX")
            }
        }
    }

}