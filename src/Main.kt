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


/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    // Constants defining any key values
    val MAX_CLICKS = 10

    // Data fields
    var clicks = 0

    // Application logic functions
    fun updateClickCount() {
        clicks++
        if (clicks > MAX_CLICKS) clicks = MAX_CLICKS
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
        locName.border = BorderFactory.createLineBorder(Color.WHITE, 2) // Add a border
        add(locName)

        locDesc = JLabel("Desc")
        locDesc.horizontalAlignment = SwingConstants.CENTER
        locDesc.bounds = Rectangle(20, 140, 290, 340)
        locDesc.font = baseFont
        locDesc.border = BorderFactory.createLineBorder(Color.WHITE, 2) // Add a border
        add(locDesc)

        locItems = JLabel("items in room")
        locItems.horizontalAlignment = SwingConstants.CENTER
        locItems.bounds = Rectangle(330, 20, 340, 220)
        locItems.font = baseFont
        locItems.border = BorderFactory.createLineBorder(Color.WHITE, 2) // Add a border
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
        o2fg.bounds = Rectangle(695, 25, 90, 13)
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



        inventory = JLabel("Inventory")
        inventory.horizontalAlignment = SwingConstants.CENTER
        inventory.bounds = Rectangle(810, 20, 170, 460)
        inventory.font = baseFont
        inventory.border = BorderFactory.createLineBorder(Color.WHITE, 2) // Add a border
        add(inventory)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        if (app.clicks == app.MAX_CLICKS) {
            locName.text = "Max clicks reached!"
            searchButton.isEnabled = false
        }
        else {
            locName.text = "Name"
            searchButton.isEnabled = true
        }
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            searchButton -> {
                app.updateClickCount()
                updateView()
            }
        }
    }
}

