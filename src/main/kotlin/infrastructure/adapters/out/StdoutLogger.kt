package infrastructure.adapters.out

import domain.ports.out.DomainLogger

class StdoutLogger: DomainLogger {
    override fun info(log: String) = println("[i] $log")
}